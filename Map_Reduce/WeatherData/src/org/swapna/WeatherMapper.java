package org.swapna;

import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.hadoop.io.ArrayWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.Writable;
import org.apache.hadoop.mapreduce.Mapper;


/*
 *  Author:Swapna
 *  
 * 
 * 
 */

public class WeatherMapper extends Mapper<LongWritable, Text, Text, IntWritable> {

	
	private Text month = new Text();
	private IntWritable maximum = new IntWritable();
	

	@Override
	public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
	
		
		String record[] = value.toString().split("\\s");
		int temperatureList[] = new int[record.length-1];
		for (int i=1;i<record.length-1;i++) {
			
			try {
				
				temperatureList[i] = Integer.parseInt(record[i]);
			}
			
			catch(NumberFormatException exception) {
				exception.printStackTrace();
			}
		}
		
		//setting month as key
		month.set(record[0]);
		
		//To get max temp
		
		int max = temperatureList[1];
		
		for (int i=2; i<temperatureList.length-2;i++)
		{
				if (temperatureList[i] > max)
					max = temperatureList[i];
		}
		
		maximum.set(max);
		context.write(month, maximum);
		
	}

}