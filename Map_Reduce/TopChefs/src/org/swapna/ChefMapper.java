package org.swapna;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.xerces.parsers.DOMParser;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/*
 * Input is XML file
 *  
 * 
 */

public class ChefMapper extends Mapper<LongWritable,Text,Text,IntWritable> {
	
	//int sum = 0;
	
	private final static IntWritable one = new IntWritable(1);
	private Text userid = new Text();
	@Override
	
	public void map(LongWritable key,Text value,Context context) throws IOException, InterruptedException {
		
		try {
			
			Map<String,String> valueMap = transformXML(value.toString());
			String userId = valueMap.get("UserID");
			userid.set(userId);
			context.write(userid,one);
			
		} catch (SAXException e) {
			
			e.printStackTrace();
		}
		
	}

	// Refer comments.xml
 public static Map<String,String> transformXML(String line) throws SAXException,IOException {
		
		Map<String,String> xmlMap = new HashMap<>();
		
		try {
			
			InputSource input = new InputSource(new ByteArrayInputStream(line.getBytes("utf-8")));
			DOMParser dom = new DOMParser();
			dom.parse(input);
		
			Document doc = dom.getDocument();
			
			NodeList nodeList = doc.getElementsByTagName("row");
			Node node = nodeList.item(0);
			NamedNodeMap  map = node.getAttributes();
			String postId = map.getNamedItem("PostId").getFirstChild().getTextContent();
			xmlMap.put("PostId", postId);
			String userId = map.getNamedItem("UserId").getFirstChild().getTextContent();
			xmlMap.put("UserID", userId);
			
		}
		
		catch(StringIndexOutOfBoundsException exception) {
			System.err.println(line);
		}
		
		return xmlMap;
	}

}

//64 gb ram 400 gb databrix 4tb
