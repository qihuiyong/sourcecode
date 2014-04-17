package com.demo.mapred.sort;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;


public class SortRun extends Configured {
	public static void main(String[] args) throws Exception {
		Configuration conf = new Configuration();
		String inPath = "D:\\mr\\in\\sort.txt", outPath = "D:\\mr\\out\\Sort"
				+ System.currentTimeMillis();
		Path in = new Path(inPath);
		Path out = new Path(outPath);
		Job job = new Job(conf, "Sort Job!");
		job.setJarByClass(SortRun.class);
//		job.setPartitionerClass(MySortPartitioner.class);
		job.setMapperClass(SortMapper.class);
//		job.setReducerClass(WordCountReducer.class);
		job.setNumReduceTasks(3);
		job.setMapOutputKeyClass(LongWritable.class);
		job.setMapOutputValueClass(Text.class);
		job.setOutputKeyClass(IntWritable.class);
		job.setOutputValueClass(Text.class);
		FileInputFormat.addInputPath(job, in);
		FileOutputFormat.setOutputPath(job, out);
		System.exit(job.waitForCompletion(true) ? 0 : 1);
	}
}
