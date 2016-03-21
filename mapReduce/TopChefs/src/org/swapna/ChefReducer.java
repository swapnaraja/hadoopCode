package org.swapna;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class ChefReducer extends Reducer<Text,IntWritable,Text,IntWritable> {
	
	IntWritable count = new IntWritable();
	
@Override
	
public void reduce(Text key,Iterable<IntWritable> value,Context context) throws IOException, InterruptedException {
	
		int sum = 0;
		for (IntWritable users:value) 
		   sum = sum + users.get();
		
		   count.set(sum);
		   context.write(key, count);
		
	}

}
