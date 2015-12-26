
package org.qhy.mapreduce.secondarySort;


import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.GenericOptionsParser;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;


public class SecondarySortDriver extends Configured implements Tool{

    @Override
    public int run(String[] arg0) throws Exception {
        
        Configuration conf = new Configuration();
        String[] otherArgs = new GenericOptionsParser(conf, arg0).getRemainingArgs();
        if (otherArgs.length != 2) {
          System.err.println("Usage: secondarysort <in> <out>");
          System.exit(2);
        }
        Job job = new Job(conf, "SECondaryqhy_sort");
        job.setJarByClass(this.getClass());
        job.setMapperClass(MapClass.class);
        job.setReducerClass(Reduce.class);

        // group and partition by the first int in the pair
        job.setPartitionerClass(FirstPartitioner.class);
        job.setGroupingComparatorClass(FirstGroupingComparator.class);

        // the map output is IntPair, IntWritable
        job.setMapOutputKeyClass(IntPair.class);
        job.setMapOutputValueClass(IntWritable.class);

        // the reduce output is Text, IntWritable
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);
        
        FileInputFormat.addInputPath(job, new Path(otherArgs[0]));
        FileOutputFormat.setOutputPath(job, new Path(otherArgs[1]));
        return job.waitForCompletion(true) ? 0 : 1;
        
        
        
//        if(arg0.length<2){
//            System.err.println(this.getClass().getSimpleName()+"必须要两个参数input,output路径");
//            ToolRunner.printGenericCommandUsage(System.err);
//            return -1;
//        }
//        Configuration conf = this.getConf();
//        Job job = new Job(conf);
//        job.setJobName("QHY_MaxNumber");
//        job.setJarByClass(this.getClass());
//        FileInputFormat.addInputPath(job, new Path(arg0[0]));
//        FileOutputFormat.setOutputPath(job, new Path(arg0[1]));
//        job.setMapperClass(MapClass.class);
//        job.setReducerClass(Reduce.class);
//        job.setOutputKeyClass(Text.class);
//        job.setOutputValueClass(IntWritable.class);
//        boolean isCompleted = job.waitForCompletion(true);
//        return isCompleted?0:1;
    }
    public static void main(String[] args) throws Exception {
        int code = ToolRunner.run(new SecondarySortDriver(), args);
        System.exit(code);
    }

}
