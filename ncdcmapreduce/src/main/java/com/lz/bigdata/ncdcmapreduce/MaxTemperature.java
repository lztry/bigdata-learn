package com.lz.bigdata.ncdcmapreduce;


import org.apache.hadoop.fs.Path;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;
// control job
public class MaxTemperature {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        if(args.length!=2){
            System.err.println("need <input path> and <output path> ");
            System.exit(-1);
        }
        Job job = Job.getInstance(); //throw IOException
        //run class name ,if there is no this class in classpath,this set is not meaning.
        job.setJarByClass(MaxTemperature.class);
        job.setJobName("Max Temperature "); //default jar name
        //set input and output path
        FileInputFormat.addInputPath(job,new Path(args[0]));
        FileOutputFormat.setOutputPath(job,new Path(args[1]));
        //set mapper and reducer class
        job.setMapperClass(MaxTemperatureMapper.class);
        job.setReducerClass(MaxTemperatureReducer.class);
        //output type
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);
        //Submit the job,true means detailed output
        System.exit(job.waitForCompletion(true)?0:1); //throw ClassNotFoundException
        // why System.exit ?

    }
}
