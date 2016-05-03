package org.swapna;
import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.MultipleInputs;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class JoinDriver {
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		Configuration conf = new Configuration();
		if (args.length != 3) {
			System.err.println("Usage: Inner Join <in> <out>");
			System.exit(2);
		}

		Job job = Job.getInstance(conf, "Inner Join");
		job.setJarByClass(JoinDriver.class);

		MultipleInputs.addInputPath(job, new Path(args[0]),
				TextInputFormat.class, EmployeeJoinMapper.class);
		MultipleInputs.addInputPath(job, new Path(args[1]),
				TextInputFormat.class, DepartmentJoinMapper.class);
		job.setReducerClass(JoinReducer.class);

		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(Text.class);

		FileOutputFormat.setOutputPath(job, new Path(args[2]));

		try {
			System.exit(job.waitForCompletion(true) ? 0 : 1);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
