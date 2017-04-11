import java.net.URI;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.*;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.jobcontrol.ControlledJob;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;
import org.apache.hadoop.filecache.DistributedCache;


public class CustomerProcessor extends Configured implements Tool {
    public static void main(String[] args) throws Exception {
        int res = ToolRunner.run(new Configuration(), new CustomerProcessor(),
                args);
        System.exit(res);
    }

    @Override
    public int run(String[] args) throws Exception {
        if (args.length < 3) {
            System.err.println("Usage:  <input_path><output_path><num_reduce_tasks>");
            System.exit(-1);
        }

		/* input parameters */
        String inputPath = args[0];
        String outputPath = args[1];
        int numReduce = Integer.parseInt(args[2]);

        //Job job = new Job(getConf(), "Customer-analysis");

        Job job = new Job(getConf());
        Configuration conf = job.getConfiguration();
        job.setJobName("Map-side join with text lookup file in DCache");
        DistributedCache.addCacheFile(new URI("/data/orders.csv"), conf);
        DistributedCache.addCacheFile(new URI("/data/products.csv"), conf);


        job.setJarByClass(CustomerProcessor.class);
        job.setMapperClass(CustomerProcessorMap.class);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(Text.class);

        //output format for mapper
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(Text.class);

        FileInputFormat.setInputPaths(job, new Path(inputPath));
        FileOutputFormat.setOutputPath(job, new Path(outputPath));
        job.setNumReduceTasks(numReduce);

        int exitStatus = job.waitForCompletion(true) ? 0 : 1;
        return exitStatus;
    }
}
