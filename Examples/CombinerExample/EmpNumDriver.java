
//REGEX : "(\"[\\w ]+, [\\w ]+\"),([\\w ]+),([\\w ]+),\\$([\\d]+\\.[\\d]{2})"

import java.io.IOException;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;


public class EmpNumDriver {
	public static void main(String[] args) throws IllegalArgumentException, IOException, ClassNotFoundException, InterruptedException{
		@SuppressWarnings("deprecation")
		Job job = new Job();
		job.setJobName("EmpNum");
		
		job.setJarByClass(EmpNumDriver.class);
		
		FileInputFormat.addInputPath(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));
		
		job.setMapperClass(EmpNumMapper.class);
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(LongWritable.class);
		
		job.setReducerClass(EmpNumReducer.class);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(LongWritable.class);
		
		//SETTING COMBINER
		job.setCombinerClass(EmpNumCombiner.class);
		
		System.exit(job.waitForCompletion(true)?0:1);
		
		
	}
}
