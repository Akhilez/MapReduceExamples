package payrollDetails;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class DetailsMapper extends Mapper<LongWritable, Text, Text, DoubleWritable>{
	public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException{
		Pattern pattern = Pattern.compile("(\"[\\w ]+, [\\w ]+\"),([\\w ]+),([\\w ]+),\\$([\\d]+\\.[\\d]{2})");
		Matcher matcher = pattern.matcher(value.toString());
		if(!matcher.matches())return;
		
		context.write(
			new Text(matcher.group(3)), 
			new DoubleWritable(Double.parseDouble(matcher.group(4)))
		);
	}
}
