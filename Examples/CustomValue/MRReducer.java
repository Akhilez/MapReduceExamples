package salCust;

import java.io.IOException;

import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.Reducer;

public class MRReducer extends Reducer<Text, CustomWritable, Text, Text>{
	public void reduce(Text key, Iterable<CustomWritable> values, Context context) throws IOException, InterruptedException{
		double sum = 0;
		StringBuilder names = new StringBuilder("\t[");
		String separator = ", ";
		for(CustomWritable cust: values){
			sum += cust.getSalary().get();
			names.append(cust.getNames().toString()+separator);
		}
		names.append("]");
		String result = sum + names.toString().replace(", ]", "]");
		
		context.write(key, new Text(result));
	}

}
