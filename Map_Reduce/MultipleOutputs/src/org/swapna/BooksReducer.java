package org.swapna;
import java.io.IOException;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.output.MultipleOutputs;

public class BooksReducer extends
		Reducer<Text, Text, Text, Text> {

	MultipleOutputs<Text,Text> multipleoutputs;
	

	@Override
	protected void setup(Context context) {
		multipleoutputs = new MultipleOutputs<>(context);
	}

	public void reduce(Text key, Iterable<Text> values, Context context) {
		
		String keywithnospaces = key.toString().replaceAll("\\s+|'", "");
		for (Text value : values) {
			
			try {
				multipleoutputs.write(key, value, keywithnospaces);
			} catch (IOException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	protected void cleanup(Context context) {
		try {
			multipleoutputs.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
