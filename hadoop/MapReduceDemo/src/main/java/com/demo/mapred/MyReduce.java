package com.demo.mapred;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class MyReduce extends Reducer<Text, Text, Text, Text> {
	protected void reduce(
			Text key,
			java.lang.Iterable<Text> value,
			Context content)
			throws java.io.IOException, InterruptedException {
		System.out.println("redc------"+key+"==========>"+value.toString());
		content.write(new Text("hello"), new Text(value+":reduce"));
	};
}
