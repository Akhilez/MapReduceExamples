package payrollDetails;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Partitioner;

public class DetailsPartitioner extends Partitioner<Text, DoubleWritable> {

	@Override
	public int getPartition(Text key, DoubleWritable value, int n) {
		char ch = key.toString().charAt(0);
		if( ch >= 'N' )
			return 1;
		else return 0;
	}

}
