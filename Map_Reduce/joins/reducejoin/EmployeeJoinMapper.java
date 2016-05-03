package org.swapna;
import java.io.IOException;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class EmployeeJoinMapper extends Mapper<Object, Text, Text, Text> {
    
	private Text outkey = new Text();
	private Text outvalue = new Text();
@Override
	public void map(Object key, Text value, Context context)
			throws IOException, InterruptedException {
		// Parse the input string into a nice map
		String[] employee = value.toString().split(",");
		String dept = employee[3];
		// The foreign join key is the user ID
		outkey.set(dept);
		// Flag this record for the reducer and then output
		String keyvalue = employee[0] + "," + employee[1] + "," + employee[2] ;
		outvalue.set("A," + keyvalue);
		context.write(outkey, outvalue);
	}
}


/*
 * key = userid
 * value = A,name,sal
 * 
 * 
 */


