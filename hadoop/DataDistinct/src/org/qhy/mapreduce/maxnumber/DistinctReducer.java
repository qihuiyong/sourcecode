
package org.qhy.mapreduce.maxnumber;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class DistinctReducer extends Reducer<Text, Text, Text, IntWritable> {

    /**
     * {@inheritDoc}使用第三方Apache Lang包
     * 
     * @see org.apache.hadoop.mapreduce.Reducer#reduce(KEYIN,
     *      java.lang.Iterable, org.apache.hadoop.mapreduce.Reducer.Context)
     */
    @Override
    protected void reduce(Text line, java.lang.Iterable<Text> numbers,
            org.apache.hadoop.mapreduce.Reducer<Text, Text, Text, IntWritable>.Context context)
            throws java.io.IOException, InterruptedException {
        String data = line.toString();
        String[] arrayVal = data.split(" ");
        String year = arrayVal[0], number = arrayVal[1];
        context.write(new Text(year), new IntWritable(Integer.valueOf(number)));
    }
}
