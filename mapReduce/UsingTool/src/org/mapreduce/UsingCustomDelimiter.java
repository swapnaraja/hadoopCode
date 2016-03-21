package org.mapreduce;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

/*
 * created on February 2015
 *  Overriding the TextInputFormat Record delimiter "/n" to "."
 *  When we have records with no delimiter (/n) need to split using different delimiter like "," or .
 */ 
 

public class UsingCustomDelimiter extends Configured implements Tool{

	
	public static void main(String[] args) throws Exception {

		int result = ToolRunner.run(new UsingCustomDelimiter(), args);
		System.exit(result);
	
	}


	@Override
	public int run(String[] args) throws Exception {
		
		Configuration conf = new Configuration();
		conf.set("textinputformat.record.delimiter", ".");
		Job job = Job.getInstance(conf);
		job.setJobName("Without newline Delimiter");
		job.setJarByClass(UsingCustomDelimiter.class);
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(NullWritable.class);
		FileInputFormat.addInputPath(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));
	
		return job.waitForCompletion(true) ? 0 : 1;
	}

}

class MapperClass extends Mapper<LongWritable, Text, Text, NullWritable> {

@Override
public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {

context.write(value,NullWritable.get());

}
}




     
