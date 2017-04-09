package salCust;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.Writable;

public class CustomWritable implements Writable {

	private DoubleWritable salary;
	private Text names;
	
	public CustomWritable(){
		salary = new DoubleWritable();
		names = new Text();
	}
	
	public CustomWritable(double sal, String name){
		salary = new DoubleWritable(sal);
		names = new Text(name);
	}
	
	public void readFields(DataInput in) throws IOException {
		salary.readFields(in);
		names.readFields(in);
	}

	public void write(DataOutput out) throws IOException {
		salary.write(out);
		names.write(out);
	}

	public DoubleWritable getSalary(){
		return salary;
	}
	
	public Text getNames(){
		return names;
	}
	
}
