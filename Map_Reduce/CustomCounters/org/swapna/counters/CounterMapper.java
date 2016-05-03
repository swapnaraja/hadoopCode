package org.swapna.counters;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
//import org.apache.hadoop.mapreduce.Mapper.Context;

public class CounterMapper extends Mapper<LongWritable,Text,LongWritable,Text> {
	
	public static enum MATCH_COUNTER {
		
		SAMPLE
	};
	
	@Override
	public void map(LongWritable key,Text value,Context context) throws IOException, InterruptedException {
		
		
		context.getCounter(MATCH_COUNTER.SAMPLE).increment(1);
		context.write(key, value);
	}

}
