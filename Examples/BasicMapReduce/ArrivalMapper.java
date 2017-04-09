import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;


public class ArrivalMapper extends Mapper<LongWritable, Text, LongWritable, LongWritable> {
    public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        if (key.get() == 0) return;
        String[] record = value.toString().split(",");
        if (record[14].equals("NA")) return;
        context.write(
                new LongWritable(Integer.parseInt(record[9])),
                new LongWritable(Integer.parseInt(record[14]))
        );
    }
}
