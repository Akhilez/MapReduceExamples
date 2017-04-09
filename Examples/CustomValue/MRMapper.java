package salCust;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.Mapper;

public class MRMapper extends Mapper<LongWritable, Text, Text, CustomWritable>{
	public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException{
		Pattern pattern = Pattern.compile("(\"[\\w ]+, ([\\w ]+)\"),([\\w ]+),([\\w ]+),\\$([\\d]+\\.[\\d]{2})");
		Matcher matcher = pattern.matcher(value.toString());
		if(!matcher.matches()) return;
		context.write(
			new Text(matcher.group(4)),
			new CustomWritable(
					Double.parseDouble(matcher.group(5)),
					matcher.group(2)
			)
		);
		
	}
}
