package payrollDetails;

import java.io.IOException;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class DetailsDriver {
	public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException{
		@SuppressWarnings("deprecation")
		Job job = new Job();
		job.setJobName("DetailsDriver");
		job.setJarByClass(DetailsDriver.class);
		
		FileInputFormat.addInputPath(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));
		
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(DoubleWritable.class);
		
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(DoubleWritable.class);
		
		job.setMapperClass(DetailsMapper.class);
		job.setReducerClass(DetailsReducer.class);
		job.setPartitionerClass(DetailsPartitioner.class); //SETTING PARTITIONER CLASS
		
		job.setNumReduceTasks(2); //SETTING NUMBER OF OUTPUT FILES
		
		System.exit(job.waitForCompletion(true)?0:1);
	}
}
