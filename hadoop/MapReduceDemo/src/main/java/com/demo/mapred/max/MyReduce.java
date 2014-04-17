package com.demo.mapred.max;

import java.util.Iterator;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class MyReduce extends Reducer<Text, IntWritable, Text, IntWritable> {
	protected void reduce(
			Text key,
			java.lang.Iterable<IntWritable> values,
			org.apache.hadoop.mapreduce.Reducer<Text, IntWritable, Text, IntWritable>.Context context)
			throws java.io.IOException, InterruptedException {
		int max = this.getAvg(values.iterator());
		System.out.println("MAA|reduce----" + key.toString() + "========>"
				+ max);
		context.write(key, new IntWritable(max));
	};

	private int getMax(Iterator<IntWritable> iterator) {
		int max = 0;
		while (iterator.hasNext()) {
			IntWritable num = iterator.next();
			if (Integer.parseInt((num.toString())) > max) {
				max = Integer.parseInt((num.toString()));
			}
		}
		return max;
	}
	private int getAvg(Iterator<IntWritable> iterator) {
		int sum = 0;
		int count=0;
		while (iterator.hasNext()) {
			count++;
			IntWritable num = iterator.next();
			sum+=Integer.parseInt((num.toString()));
		}
		return sum/count;
	}
}
