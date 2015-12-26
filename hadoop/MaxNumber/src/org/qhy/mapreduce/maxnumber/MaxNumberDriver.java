
package org.qhy.mapreduce.maxnumber;


import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;


public class MaxNumberDriver extends Configured implements Tool{

    @Override
    public int run(String[] arg0) throws Exception {
        if(arg0.length<2){
            System.err.println(this.getClass().getSimpleName()+"必须要两个参数input,output路径");
            ToolRunner.printGenericCommandUsage(System.err);
            return -1;
        }
        Configuration conf = this.getConf();
        Job job = new Job(conf);
        job.setJobName("QHY_MaxNumber");
        job.setJarByClass(this.getClass());
        FileInputFormat.addInputPath(job, new Path(arg0[0]));
        FileOutputFormat.setOutputPath(job, new Path(arg0[1]));
        job.setMapperClass(MapClass.class);
        job.setReducerClass(Reduce.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);
        boolean isCompleted = job.waitForCompletion(true);
        return isCompleted?0:1;
    }
    public static void main(String[] args) throws Exception {
        int code = ToolRunner.run(new MaxNumberDriver(), args);
        System.exit(code);
    }

}
