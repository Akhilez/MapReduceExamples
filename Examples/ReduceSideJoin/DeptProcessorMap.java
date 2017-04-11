import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class DeptProcessorMap extends Mapper<Object, Text, IntWritable, Text> {
    private String fileTag = "DF";//this tag is to differentiate the input file data at reducer side.

    public void map(Object key, Text value, Context context)
            throws IOException, InterruptedException {     //taking one line/record at a time and parsing them into key value pairs
        String line = value.toString();
        String splitarray[] = line.split(",");
        int deptNumber = Integer.parseInt(splitarray[0].trim());
        String deptName = splitarray[1].trim();
        String location = splitarray[2].trim();
        context.write(new IntWritable(deptNumber), new Text
                (fileTag + ',' + deptName + ',' + location));
    }
}
