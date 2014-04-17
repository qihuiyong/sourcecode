package com.demo.mapred.wordCount;

import java.io.IOException;

import org.apache.commons.lang3.StringUtils;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import com.asiainfo.biframe.utils.string.StringUtil;

public class WordCountMapper extends Mapper<Object, Text, Text,IntWritable > {

	@Override
	protected void map(Object key, Text value,
			org.apache.hadoop.mapreduce.Mapper<Object, Text, Text,IntWritable >.Context context)
			throws IOException, InterruptedException {
		if(value!=null && StringUtils.isNotEmpty(value.toString())){
			String[] values=value.toString().split(" ");
			for (int i = 0;  values !=null&& i < values.length; i++) {
				String outKey=values[i].trim();
				context.write(new Text(outKey), new IntWritable((int) (outKey.length()*(Math.random()*10))));
			}
		}
	}

}
