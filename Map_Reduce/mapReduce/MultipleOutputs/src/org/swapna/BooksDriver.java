package org.swapna;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

/* Created on January 2015
 * 
 * 
 */

public class BooksDriver  extends Configured implements Tool {

	public static void main(String[] args) throws Exception {
       
		int result = ToolRunner.run(new BooksDriver(), args);
		System.exit(result);
	
	}

	@Override
	public int run(String[] args) throws Exception {
		
		Configuration conf = new Configuration();
		Job job = Job.getInstance(conf);
		job.setJarByClass(BooksDriver.class);
		job.setJobName("Books");
		
		job.setMapperClass(BooksMapper.class);
		job.setCombinerClass(BooksReducer.class);
		job.setReducerClass(BooksReducer.class);
		
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(Text.class);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(Text.class);
		
		FileInputFormat.addInputPath(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));
	
		return job.waitForCompletion(true) ? 0 : 1;
	}
}


