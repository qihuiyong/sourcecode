package com.demo.mapred;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class MyMapper extends Mapper<Object, Text, Text, Text> {
	protected void map(
			Object key,
			Text value,
			Context context)
			throws java.io.IOException, InterruptedException {
		context.write(new Text("mk:"+key), new Text(String.valueOf(3)));
	};
}
