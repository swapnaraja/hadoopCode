package org.swapna;
import java.io.IOException;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class DepartmentJoinMapper  extends Mapper<Object, Text, Text, Text> {

	private Text outkey = new Text();
	private Text outvalue = new Text();
@Override
	public void map(Object key, Text value, Context context)
			throws IOException, InterruptedException {
		// Parse the input string into a nice map
		String[] department = value.toString().split(",");
		String dept = department[0];
		// The foreign join key is the user ID
		outkey.set(dept);
		// Flag this record for the reducer and then output
		String keyvalue = department[0] + "," + department[1];
		outvalue.set("B," + keyvalue);
		context.write(outkey, outvalue);
	}
}

/*
 * key = userid
 * value = Bdid,name
 * 
 * 
 * 
 */
