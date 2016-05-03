package org.swapna;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.Reducer.Context;



/*
 *  first mapper gives key as empid
 *  value as empname,salary
 * 
 * second mapper gives key as empid
 * value as deptname,deptid
 * 
 * Reducer gets (empid,A,empname,salary)
 * (empid,B,deptid,deptname)
 * 
 * 
 * 
 */
public class JoinReducer extends Reducer<Text, Text, Text, Text> {

			private ArrayList<Text> listA = new ArrayList<Text>();
			private ArrayList<Text> listB = new ArrayList<Text>();

			private String joinType = "inner";

			public void setup(Context context) {
				// Get the type of join from our configuration
				joinType = context.getConfiguration().get("join.type");
			}
@Override

public void reduce(Text key, Iterable<Text> values,Context context) throws IOException,InterruptedException {
			
				// Clear our lists
				listA.clear();
				listB.clear();

				// iterate through all our values, binning each record based on what
				// it was tagged with. Make sure to remove the tag!
				for (Text value : values) {
					String[] tmp = value.toString().split(",");

					if (tmp[0].equals("A")) {
						listA.add(new Text(tmp[1]));
						listA.add(new Text(tmp[2]));
						listA.add(new Text(tmp[3]));

					} else if (tmp[0].equals("B")) {
						listB.add(new Text(tmp[1]));
						listB.add(new Text(tmp[2]));
					}
				}
				// Execute our join logic now that the lists are filled
				executeJoinLogic(key, context);
			}

			private void executeJoinLogic(Text key, Context context)
					throws IOException, InterruptedException {
				
				System.out.println("Inside excute ");
				System.out.println(listA.size());
				System.out.println(listB.size());

				if (joinType.equalsIgnoreCase("inner")) {
					// If both lists are not empty, join A with B
					if (!listA.isEmpty() && !listB.isEmpty()) {

						String value = listA.get(0).toString() + "," + listA.get(1).toString() + ","
								+ listB.get(0).toString() + "," + listB.get(1).toString();
						System.out.println("value"+value);
					
						context.write(key, new Text(value));
					}
				}
			}
		}

