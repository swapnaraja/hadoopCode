//Counters are useful for debugging the hadoop code. there are many builtin counters 
//if we want can add user defined counters



package org.swapna.counters;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Counter;
import org.apache.hadoop.mapreduce.CounterGroup;
import org.apache.hadoop.mapreduce.Counters;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.swapna.counters.CounterMapper.MATCH_COUNTER;

public class Driver {

	public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
		// TODO Auto-generated method stub
 Configuration conf = new Configuration();
 Job job = Job.getInstance(conf);
 
 job.setJarByClass(Driver.class);
 job.setMapperClass(CounterMapper.class);
 
 job.setMapOutputKeyClass(LongWritable.class);
 job.setMapOutputValueClass(Text.class);
 
 FileInputFormat.addInputPath(job,new Path(args[0])); 
 FileOutputFormat.setOutputPath(job, new Path(args[1]));
 
    System.exit(job.waitForCompletion(true) ? 0:1);
	Counter counter = job.getCounters().findCounter(MATCH_COUNTER.SAMPLE); //user defined
	System.out.println(counter.getValue());

	/*for (CounterGroup group:counters) // built in 
	{
		System.out.println(group.getDisplayName()+","+group.getName()+","+group.size());
		
		for (Counter counter:group) {
			
			System.out.println(counter.getDisplayName()+","+counter.getName()+","+counter.getValue());
		}
	}*/
	
	
	
	
	
	}

}
