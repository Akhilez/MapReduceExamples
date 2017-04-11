import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class LogProcessorMap extends Mapper<Object, Text, Text, LongWritable> {
    private Text userHostText = new Text();
    private LongWritable responseSize = new LongWritable();

    public void map(Object key, Text value, Context context)
            throws IOException, InterruptedException {
        String logEntryPattern = "^(\\S+) (\\S+) (\\S+) \\[([\\w:/]+\\s[+\\-]\\d{4})\\] \"(.+?)\" (\\d{3}) (\\d+)";

        Pattern p = Pattern.compile(logEntryPattern);
        Matcher matcher = p.matcher(value.toString());
        if (!matcher.matches()) {
            return;
        }

        String userHost = matcher.group(1);
        userHostText.set(userHost);
        Long size = Long.parseLong(matcher.group(7));
        responseSize.set(size);

        context.write(userHostText, responseSize);


    }
}