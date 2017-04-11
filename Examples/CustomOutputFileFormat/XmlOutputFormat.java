import java.io.*;

import org.apache.hadoop.mapreduce.*;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.lib.input.*;
import org.apache.hadoop.mapreduce.lib.output.*;
import org.apache.hadoop.fs.*;

public class XmlOutputFormat extends FileOutputFormat<Text, LongWritable> {

    public RecordWriter<Text, LongWritable> getRecordWriter(TaskAttemptContext arg0) throws IOException, InterruptedException {
        //get the current path
        Path path = FileOutputFormat.getOutputPath(arg0);
        //create the full path with the output directory plus our filename
        Path fullPath = new Path(path, "result.xml");

        //create the file in the file system
        FileSystem fs = path.getFileSystem(arg0.getConfiguration());
        FSDataOutputStream fileOut = fs.create(fullPath, arg0);

        //create our record writer with the new file
        return new CustomRecordWriter(fileOut);
    }
}