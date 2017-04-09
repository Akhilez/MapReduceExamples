package salCust;

import java.io.IOException;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class MRDriver {
	public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException{
		@SuppressWarnings("deprecation")
		Job job = new Job();
		job.setJobName("SalCust");
		job.setJarByClass(MRDriver.class);
		
		FileInputFormat.addInputPath(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));
		
		job.setMapperClass(MRMapper.class);
		job.setReducerClass(MRReducer.class);
		
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(CustomWritable.class);//SETTING CUSTOM VALUE
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(Text.class);
		
		System.exit(job.waitForCompletion(true)? 0 : 1);
		
	}
}
  