
package org.qhy.mapreduce.maxnumber;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class DistinctMapper extends Mapper<LongWritable, Text, Text, Text> {

    @Override
    protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, Text>.Context context)
            throws IOException, InterruptedException {
        String lineVal = value.toString();
//        String[] arrayVal = lineVal.split(" ");
//        String year= arrayVal[0],number=arrayVal[1];
//        context.write(new Text(year), new IntWritable(Integer.parseInt(number)));
        context.write(new Text(lineVal), new Text(""));
    }
}
