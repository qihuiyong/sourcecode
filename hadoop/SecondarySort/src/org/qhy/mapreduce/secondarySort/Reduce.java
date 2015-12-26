
package org.qhy.mapreduce.secondarySort;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang3.math.NumberUtils;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class Reduce extends Reducer<IntPair, IntWritable, Text, IntWritable> {

    private static final Text SEPARATOR = new Text("------------------------------------------------");

    private final Text first = new Text();

    @Override
    public void reduce(IntPair key, Iterable<IntWritable> values, Context context) throws IOException,
            InterruptedException {
        context.write(SEPARATOR, null);
        first.set(Integer.toString(key.getFirst()));
        for (IntWritable value : values) {
            context.write(first, value);
        }
    }
}
