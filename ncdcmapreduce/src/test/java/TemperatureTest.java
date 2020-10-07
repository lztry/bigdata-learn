import com.lz.bigdata.ncdcmapreduce.MaxTemperatureDriver;
import com.lz.bigdata.ncdcmapreduce.MaxTemperatureMapper;
import com.lz.bigdata.ncdcmapreduce.MaxTemperatureReducer;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mrunit.mapreduce.MapDriver;
//the name of new version mapreduce api is mapreduce instead of mapred or mrunit.MapDriver
import org.apache.hadoop.mrunit.mapreduce.ReduceDriver;
import org.apache.hadoop.mrunit.types.Pair;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class TemperatureTest {
    @Test
    public void testMapper(){
        Text text = new Text("0096007026999992016062218244+00000+000000FM-15+702699999V0209999C000019999999N999999999+03401+01801999999ADDMA1101731999999REMMET069MOBOB0 METAR 7026 //008 000000 221824Z AUTO 00000KT  34/18 A3004=");
        try {
            /*
            new MapDriver<LongWritable,Text,Text, IntWritable>()
                    .withMapper(new MaxTemperatureMapper())
                    .withInput(new LongWritable(0),text)
                    .withOutput(new Text("2016"),new IntWritable(340))
                    .runTest();
             */
            MapDriver<LongWritable, Text, Text, IntWritable> mapDriver = new MapDriver<>();
            mapDriver.withMapper(new MaxTemperatureMapper())
                     .withInput(new LongWritable(0),text)
                    //expected output value
                     .withOutput(new Text("2016"),new IntWritable(340))
                     .runTest();
            // output
            List<Pair<Text, IntWritable>> expectedOutputs = mapDriver.getExpectedOutputs();
            for(Pair<Text, IntWritable> pair : expectedOutputs){
                System.out.println(pair.getFirst() + " --- " + pair.getSecond()); // 2016 --- 340
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void testReducer(){
        ReduceDriver<Text, IntWritable, Text, IntWritable> reduceDriver = new ReduceDriver<>();

        try {
            reduceDriver.withReducer(new MaxTemperatureReducer())
                    .withInput(new Text("2015"), Arrays.asList(new IntWritable(240),new IntWritable(340)))
                    .withOutput(new Text("2015"),new IntWritable(340))
                    .runTest();
            // no exception prove no problem
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //can debug
    @Test
    public void testDriver(){
        Configuration conf = new Configuration();
        // set local file system
        conf.set("fs.defaultFS","file:///");
        conf.set("mapreduce.framework.name","local");
        conf.setInt("mapreduce.task.io.sort.mb",1);
        Path inputPath = new Path("/home/lz/code/bigdata/data/pub/data/noaa/1901");
        Path outputPath = new Path("output");
        MaxTemperatureDriver driver = new MaxTemperatureDriver();
        driver.setConf(conf);
        try {
            driver.run(new String[]{inputPath.toString(),outputPath.toString()});
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    enum TemTest{
        OVER_100
    }
    @Test
    public void testEnum(){
        System.out.println(TemTest.OVER_100);
    }
    @Test


}
