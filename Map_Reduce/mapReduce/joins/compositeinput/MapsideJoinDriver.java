package org.mapreduce;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.KeyValueTextInputFormat;
import org.apache.hadoop.mapreduce.lib.join.CompositeInputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;

public class MapsideJoinDriver {

	public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
		// TODO Auto-generated method stub
Configuration conf = new Configuration();
conf.set("mapreduce.input.keyvaluelinerecordreader.key.value.separator", ",");
Job job = Job.getInstance(conf);
job.setJarByClass(MapsideJoinDriver.class);
job.setMapperClass(MapsideJoinMapper.class);
job.setInputFormatClass(CompositeInputFormat.class);
String expr = CompositeInputFormat.compose("inner",KeyValueTextInputFormat.class , new Path(args[0]),new Path(args[1]));
//String expr = CompositeInputFormat.compose("outer",KeyValueTextInputFormat.class , new Path(args[0]),new Path(args[1]));
job.getConfiguration().set("mapreduce.join.expr",expr);
job.setNumReduceTasks(0);
TextOutputFormat.setOutputPath(job, new Path(args[2]));
job.waitForCompletion(true);
	}

}
