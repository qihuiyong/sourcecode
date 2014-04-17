package com.demo.mapred.sort;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class SortMapper extends Mapper<LongWritable, Text, IntWritable, Text> {
	protected void map(
			IntWritable key,
			Text value,
			org.apache.hadoop.mapreduce.Mapper<LongWritable, Text, IntWritable, Text>.Context context)
			throws java.io.IOException, InterruptedException {
		Integer number = new Integer(value.toString().trim());
		context.write(new IntWritable(number), new Text("hello"));
	}
}
