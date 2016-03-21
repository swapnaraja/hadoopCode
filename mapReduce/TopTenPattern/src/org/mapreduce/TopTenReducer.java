package org.mapreduce;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class TopTenReducer extends Reducer<NullWritable, Text, NullWritable, Text>{
	List<Employee> list = new ArrayList<>();
	Employee emp;
	Text outvalue = new Text();
	
	@Override
	public void reduce(NullWritable key, Iterable<Text> values,Context context) throws IOException, InterruptedException {
		
		for (Text text:values) {
		String record[] = text.toString().split(",");
		String name = record[0];
		double sal = Double.parseDouble(record[1]);
		emp = new Employee(name,sal);
		list.add(emp);	
		}
		
		Collections.sort(list, new MySalaryComprator());
		
		for (Employee emp:list.subList(0, 10)) {
			outvalue.set(emp.getName()+","+emp.getSal());
			context.write(NullWritable.get(), outvalue);
		}
		
	}

}






