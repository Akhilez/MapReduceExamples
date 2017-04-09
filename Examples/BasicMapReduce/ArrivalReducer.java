
import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.mapreduce.Reducer;


public class ArrivalReducer extends Reducer<LongWritable, LongWritable, LongWritable, LongWritable> {
    public void reduce(LongWritable key, Iterable<LongWritable> values, Context context)
            throws IOException, InterruptedException {
        int sum = 0, count = 0;
        for (LongWritable delay : values) {
            sum += delay.get();
            count++;
        }
        context.write(
                key,
                new LongWritable((long) (sum / count))
        );
    }
}
