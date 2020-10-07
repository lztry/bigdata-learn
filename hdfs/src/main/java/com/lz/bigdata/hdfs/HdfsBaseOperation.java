package com.lz.bigdata.hdfs;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.util.Progressable;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;

public class HdfsBaseOperation {
    public static void print(String uri) throws IOException {
        FileSystem fs = FileSystem.get(URI.create("hdfs://client.lwh.com:8020"), new Configuration());
        FSDataInputStream inputStream = null;
        try {
            inputStream = fs.open(new Path(uri));
            IOUtils.copyBytes(inputStream, System.out, 4096, false);
            System.out.println("\n-------------------------------");
            // absolute location of file,It has high performance overhead
            inputStream.seek(4);
            IOUtils.copyBytes(inputStream, System.out, 4096, false);
        } finally {
            IOUtils.closeStream(inputStream);
        }
    }
    //write create
    public static void create(String localDir,String hdfsDir) throws IOException, InterruptedException {
        FileSystem fs = FileSystem.get(URI.create("hdfs://client.lwh.com:8020"), new Configuration(),"hdfs");
        // there are many parameters in create function,for example progress, overwrite or not
        // callback Progressable interface show progress
        FSDataOutputStream outputStream = fs.create(new Path(hdfsDir), true, 4096, new Progressable() {
            @Override
            public void progress() {
                System.out.println("*");
            }
        });
        InputStream inputStream = new FileInputStream(localDir);
        IOUtils.copyBytes(inputStream,outputStream,4096,true);

    }
    //write append likes create
    public static void append(){

    }
    // file status
    public static void fileStatus(String path) throws IOException, InterruptedException {
        FileSystem fs = FileSystem.get(URI.create("hdfs://client.lwh.com:8020"), new Configuration(),"hdfs");
        FileStatus fileStatus = fs.getFileStatus(new Path(path));
        System.out.println(fileStatus.toString());
    }
    //listStatus(Path[] files)  lists several files status or lists directory all files
    //globbing
    public static void globbing() throws IOException, InterruptedException {
        FileSystem fs = FileSystem.get(URI.create("hdfs://client.lwh.com:8020"), new Configuration(),"hdfs");
        FileStatus[] fileStatuses = fs.globStatus(new Path("/*"),new RegexExcludePathFilter(""));
        //状态转换为path
        Path[] paths = FileUtil.stat2Paths(fileStatuses);
        for(Path p : paths){
            System.out.println(p);
        }

    }
    public static void main(String[] args) throws IOException {
        //print("/tmp/tezsmokeinput/sample-tez-test");
        try {
            create("/home/lz/temperature","/test/lz/temperature/");
            //fileStatus("/data/result");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //print("/data/result/create");
    }
}
//根据正则表达式进行过滤,因为globStatus第一个参数已经进行了正则匹配,这个值可以用作排除元素
class RegexExcludePathFilter implements PathFilter{
    private final String regex;
    public RegexExcludePathFilter(String regex){
        this.regex = regex;
    }
    @Override
    public boolean accept(Path path) {
        return !path.toString().matches(regex);
    }
}