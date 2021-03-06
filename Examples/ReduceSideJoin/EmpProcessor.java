import java.net.URI;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.*;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.*;
import org.apache.hadoop.mapreduce.lib.jobcontrol.ControlledJob;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;
import org.apache.hadoop.mapreduce.lib.input.MultipleInputs;

public class EmpProcessor extends Configured implements Tool {
    public static void main(String[] args) throws Exception {
        int res = ToolRunner.run(new Configuration(), new EmpProcessor(), args);
        System.exit(res);
    }

    @Override
    public int run(String[] args) throws Exception {
        if (args.length < 4) {
            System.err.println("Usage:  <input_path1><input_path2><output_path>
                            < num_reduce_tasks > ");
                    System.exit(-1);
        }
/* input parameters */
        String outputPath = args[2];
        int numReduce = Integer.parseInt(args[3]);

        @SuppressWarnings("deprecation")
        Configuration conf = new Configuration();
        Job job = new Job(conf, "employee-data-analysis");

        job.setJarByClass(EmpProcessor.class);

//Specifying the input directories(@ runtime) &Mappers independently for inputs from multiple sources
        MultipleInputs.addInputPath(job, new Path(args[0]), TextInputFormat.class,
                EmpProcessorMap.class);
        MultipleInputs.addInputPath(job, new Path(args[1]), TextInputFormat.class,
                DeptProcessorMap.class);

        job.setReducerClass(EmpProcessorReducer.class);

//output format for mapper
        job.setMapOutputKeyClass(IntWritable.class);
        job.setMapOutputValueClass(Text.class);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(Text.class);

        FileOutputFormat.setOutputPath(job, new Path(outputPath));
        job.setNumReduceTasks(numReduce);

        int exitStatus = job.waitForCompletion(true) ? 0 : 1;
        return exitStatus;
    }
}
