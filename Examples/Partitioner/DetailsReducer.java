package payrollDetails;

import java.io.IOException;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class DetailsReducer extends Reducer<Text, DoubleWritable, Text, DoubleWritable>{
	public void reduce(Text key, Iterable<DoubleWritable> values, Context context) throws IOException, InterruptedException{
		double total = 0;
		for(DoubleWritable sal: values)
			total += sal.get();
		context.write(key, new DoubleWritable(total));
	}

}
