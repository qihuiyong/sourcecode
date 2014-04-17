package com.demo.mapred.max;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class MyMapper extends Mapper<Object, Text, Text, IntWritable> {
	protected void map(
			Object key,
			Text value,
			org.apache.hadoop.mapreduce.Mapper<Object, Text, Text, IntWritable>.Context context)
			throws java.io.IOException, InterruptedException {
		String year= value.toString().substring(0, 4);
		String number= value.toString().substring(9);
		System.out.println("MAA|mapper----"+year+"========>"+number);
		context.write(new Text(year), new IntWritable(Integer.parseInt(number)));
	};
}
