package org.mapreduce;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.join.TupleWritable;

public class MapsideJoinMapper extends Mapper<Text,TupleWritable,Text,Text>{
	Text textVal = new Text();
@Override
public void map(Text key,TupleWritable value,Context context) throws IOException, InterruptedException {
	
	//System.err.println(value.toString());
	System.out.println("key is"+key.toString());
	System.out.println("valueis"+value.toString());
	//String[] record = value.toString().split(",");
	String emp = value.get(0).toString();
	String dept = value.get(1).toString();
	textVal.set(emp+","+dept);
	context.write(key, textVal);
	
	
}
}
