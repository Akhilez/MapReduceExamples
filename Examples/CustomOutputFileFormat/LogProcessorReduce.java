import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.Writable;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.io.*;


public class LogProcessorReduce extends
        Reducer<Text, LongWritable, Text, LongWritable> {


    public void reduce(Text key, Iterable<LongWritable> values,
                       Context context) throws IOException, InterruptedException {

        for (LongWritable value : values) {
            context.write(key, value);
        }
    }
}