package com.demo.mapred.wordCount;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class WordCountRun extends Configured {
	public static void main(String[] args) throws Exception {
		Configuration conf = new Configuration();
		String inPath = "D:\\mr\\in\\wordcount.txt", outPath = "D:\\mr\\out\\WordCount"
				+ System.currentTimeMillis();
		Path in = new Path(inPath);
		Path out = new Path(outPath);
		Job job = new Job(conf, "WordCount Job!");
		job.setJarByClass(WordCountRun.class);
		job.setMapperClass(WordCountMapper.class);
		job.setReducerClass(WordCountReducer.class);
		job.setNumReduceTasks(3);
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(IntWritable.class);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);
		FileInputFormat.addInputPath(job, in);
		FileOutputFormat.setOutputPath(job, out);
		System.exit(job.waitForCompletion(true) ? 0 : 1);
	}
}
