package com.demo.mapred.wordCount;

import java.io.IOException;
import java.util.Iterator;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class WordCountReducer extends
		Reducer<Text, IntWritable, Text, IntWritable> {

	protected void reduce(Text key, Iterable<IntWritable> values,
			org.apache.hadoop.mapreduce.Reducer<Text, IntWritable, Text, IntWritable>.Context context)
			throws IOException, InterruptedException {
		int sum=0;
		Iterator<IntWritable > it = values.iterator();
		while(it.hasNext()){
			it.next();
			sum++;
		}
		System.out.println("reduce====>"+key+"----------------------"+sum);
		context.write(key, new IntWritable(sum));
	}

}
