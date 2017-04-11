import java.io.*;

import org.apache.hadoop.mapreduce.*;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.lib.input.*;
import org.apache.hadoop.mapreduce.lib.output.*;
import org.apache.hadoop.fs.*;

public class CustomRecordWriter extends RecordWriter<Text, LongWritable> {
    private DataOutputStream out;

    public CustomRecordWriter(DataOutputStream stream) {
        out = stream;
        try {
            out.writeBytes("<results>\n");
        } catch (Exception ex) {
        }
    }


    public void close(TaskAttemptContext arg0) throws IOException, InterruptedException {
        //close our file
        try {
            out.writeBytes("</results>");
        } finally {
            // even if writeBytes() fails, make sure we close the stream
            out.close();
        }

    }

    public void write(Text arg0, LongWritable arg1) throws IOException, InterruptedException {
        //write out our key
        out.writeBytes("<result>");
        out.writeBytes("\r\n<key>");
        out.writeBytes(arg0.toString());
        out.writeBytes("</key>");
        out.writeBytes("\r\n<value>");
        out.writeBytes(arg1.toString());
        out.writeBytes("</value>\r\n");
        out.writeBytes("</result>");
        out.writeBytes("\r\n");
    }
}