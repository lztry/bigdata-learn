package com.lz.bigdata.ncdcmapreduce;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

public class MaxTemperatureDriver extends Configured implements Tool {
    /**
     * 1、通过使用ToolRunner.run(...)方法，可以更便利的使用hadoop命令行参数。
     * 2、ToolRunner.run(...)通过调用Tool类中的run(String[])方法来运行hadoop程序，并默认加载core-default.xml与core-site.xml中的参数。
     * */
    @Override
    public int run(String[] strings) throws Exception {
        if(strings.length!=2){
            System.err.println("need <input path> and <output path> ");
            System.exit(-1);
        }
        Job job = Job.getInstance(); //throw IOException
        //run class name ,if there is no this class in classpath,this set is not meaning.
        job.setJarByClass(MaxTemperature.class);
        job.setJobName("Max Temperature "); //default jar name
        //set input and output path
        FileInputFormat.addInputPath(job,new Path(strings[0]));
        FileOutputFormat.setOutputPath(job,new Path(strings[1]));
        //set mapper and reducer class
        job.setMapperClass(MaxTemperatureMapper.class);
        job.setReducerClass(MaxTemperatureReducer.class);
        //output type
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);
        //Submit the job,true means detailed output
        return job.waitForCompletion(true)?0:1;
        //System.exit(job.waitForCompletion(true)?0:1); //throw ClassNotFoundException
    }

    public static void main(String[] args) throws Exception {
        System.exit(ToolRunner.run(new MaxTemperatureDriver(),args));
    }

    @Override
    public void setConf(Configuration configuration) {

    }

    @Override
    public Configuration getConf() {
        return null;
    }
}
