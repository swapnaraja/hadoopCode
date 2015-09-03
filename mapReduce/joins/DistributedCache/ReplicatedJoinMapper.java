package org.mapreduce;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.util.HashMap;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.filecache.DistributedCache;
import org.apache.hadoop.util.GenericOptionsParser;

public class ReplicatedJoinMapper extends Mapper<LongWritable, Text, Text,Text > {
	HashMap<String,String> deptMap = new HashMap<>();
	Text outpkey = new Text();
	String deptname = "";
	Text outpvalue = new Text();
	@Override
	public void setup(Context context) throws IOException {
	 //Path[] localCacheFiles = DistributedCache.getLocalCacheFiles(context.getConfiguration());
	 URI[] paths  = context.getCacheFiles();
	 Path p = new Path(paths[0]);
	 //URL fileurl = paths.
     loadDept(p,context);	 

	 
		
	}
	
	private void loadDept(Path p,Context context) 
			throws IOException {
		// TODO Auto-generated method stub
		
		BufferedReader buffer = new BufferedReader(new FileReader(p.toString()));
		String strLine = "";
		while((strLine = buffer.readLine())!=null) {
			
			String deptFile[] = strLine.split(",");
			deptMap.put(deptFile[0], deptFile[1]);
			
		}
		buffer.close();
		
	}

	
	

	@Override
	public void map(LongWritable key,Text value,Context context) throws IOException, InterruptedException {
		String emp[] = value.toString().split(",");
		deptname = deptMap.get(emp[3]);
		outpkey.set(emp[0]);
		outpvalue.set(emp[1] + "\t" + emp[2] + "\t" + emp[3] + "\t" + deptname);
		context.write(outpkey,  outpvalue);
	    
	}

}
