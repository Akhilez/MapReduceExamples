import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;


public class EmpNumMapper extends Mapper<LongWritable, Text, Text, LongWritable>{
	public void map(LongWritable key, Text value, Context context)
		throws IOException, InterruptedException{
		
		//CHECK IF THE LINE/VALUE MATCHES WITH THE PATTERN
		String patternString = "(\"[\\w ]+, [\\w ]+\"),([\\w ]+),([\\w ]+),\\$([\\d]+\\.[\\d]{2})";
		Pattern pattern = Pattern.compile(patternString);
		Matcher m = pattern.matcher(value.toString());
		if(m.matches()){
			context.write(new Text(m.group(3)), new LongWritable(1));
		}
	}
}
