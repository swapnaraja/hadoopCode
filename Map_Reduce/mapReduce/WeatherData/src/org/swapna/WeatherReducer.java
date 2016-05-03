package org.swapna;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class WeatherReducer extends Reducer<Text,IntWritable,Text,IntWritable> {
	
	IntWritable maximum = new IntWritable();
@Override
	
public void reduce(Text key,Iterable<IntWritable> values,Context context) throws IOException, InterruptedException {
	
	int max = Integer.MIN_VALUE;
	   
		for (IntWritable value:values) {	
		 max = Math.max(max, value.get());
		}
		
		maximum.set(max);
		context.write(key,maximum );
	}

}

