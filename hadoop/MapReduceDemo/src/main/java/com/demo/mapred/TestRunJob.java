package com.demo.mapred;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;


public class TestRunJob  extends Configured{
	public static void main(String[] args) {
		try{
			Configuration conf = new Configuration();
//			String file = "/config/classifyDimCount.properties";
			String inPath = "D:\\mr\\in\\a.txt";
			String outPath = "D:\\mr\\out\\"+System.currentTimeMillis();
			
			Path out = new Path(outPath); 
			
			Job job = new Job(conf, "classify dim genetator");
			
			job.setJarByClass(TestRunJob.class);
			job.setMapperClass(MyMapper.class);
			job.setReducerClass(MyReduce.class);
			job.setNumReduceTasks(1);
			job.setMapOutputKeyClass(Text.class);
			job.setMapOutputValueClass(Text.class);
//			job.setOutputFormatClass(DimOutputFormat.class);
			job.setOutputKeyClass(Text.class);
			job.setOutputValueClass(Text.class);
			FileInputFormat.addInputPath(job, new Path(inPath));
			FileOutputFormat.setOutputPath(job, out);
			
			System.exit(job.waitForCompletion(true) ? 0 : 1);
		}
		catch (InterruptedException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (ClassNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
