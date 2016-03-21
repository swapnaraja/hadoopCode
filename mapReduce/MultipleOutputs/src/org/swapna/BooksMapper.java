package org.swapna;

import java.io.IOException;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class BooksMapper extends Mapper<LongWritable, Text, Text, Text> {

//Filtering the records on category
	Text category = new Text("");
	Text txtValue = new Text("");

	@Override
	protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
		
		String line = value.toString();
		String[] record = line.split(",");
		category.set(record[4]);
		txtValue.set(record[0]+"\t"+record[1].toString()+"\t"+record[2].toString());
		context.write(category, txtValue);
		
	}

}



