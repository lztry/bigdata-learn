package com.lz.bigdata.ncdcmapreduce;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import java.io.IOException;

// the first two are input type
//max temperature input int and sting ,but hadoop serialize types
// note: this is extends instead of implement
public class MaxTemperatureMapper extends Mapper<LongWritable, Text,Text, IntWritable> {
    //missing data = 9999
    private static final int MISSING = 9999;
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String line = value.toString();
        String year = line.substring(15,19);
        int temperature = Integer.parseInt(line.substring(87,92));
        String quality = line.substring(92,93);
        if(temperature !=MISSING && quality.matches("[01459]")){
            context.setStatus();//Set the current status of the task to the given string.
            context.write(new Text(year),new IntWritable(temperature));
            //context.getCounter().increment(1);
        }
        
    }
    //map function gets temperature values

}
