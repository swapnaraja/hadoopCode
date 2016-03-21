package org.swapna;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

/*
 * created on july 2015
 * 
 * 
 */


public class WeatherReport extends Configured implements Tool{

	public static void main(String[] args) throws Exception {
		int result = ToolRunner.run(new WeatherReport(), args);
		System.exit(result);

	}

	@Override
	public int run(String[] args) throws Exception {

		Configuration conf = new Configuration();
		Job job = Job.getInstance(conf);
		job.setJarByClass(WeatherReport.class);
		job.setJobName("WeatherData");
		
		job.setMapperClass(WeatherMapper.class);
		job.setReducerClass(WeatherReducer.class);
		
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(IntWritable.class);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);
		
		FileInputFormat.addInputPath(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));
	
		return job.waitForCompletion(true) ? 0 : 1;
	}

}
