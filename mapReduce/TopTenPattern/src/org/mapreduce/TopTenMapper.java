package org.mapreduce;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;


/*
 * Display top ten salaried employees 
 * Using List
 * 
 * 
 */

public class TopTenMapper extends Mapper<LongWritable, Text, NullWritable, Text> {
	
	List <Employee> list = new ArrayList<>();
	Employee emp ;
	Text outvalue = new Text();

	@Override
	public void map(LongWritable key,Text value,Context context) throws IOException, InterruptedException {
		
	String record[] = value.toString().split(",");
	
	String name = record[1];
	double sal = Double.parseDouble(record[2]);
	
    emp = new Employee(name,sal);
   
   list.add(emp); 
   
   Collections.sort(list, new MySalaryComprator());  
 
	}
	
	
	@Override
	public void cleanup(Context context) throws IOException,InterruptedException {
		
		for (Employee emp:list.subList(0, 10)) {
			
			outvalue.set(emp.getName()+","+emp.getSal());
			context.write(NullWritable.get(),outvalue);
		}
			
			
		
	}

}
