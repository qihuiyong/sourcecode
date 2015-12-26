
package org.qhy.mapreduce.test;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.lang3.math.NumberUtils;
import org.apache.hadoop.io.IntWritable;


public class Test {

    /**
     * Description: 
     *
     * @param args
     */
    public static void main(String[] args) {
        Map<String, IntWritable> map = new HashMap<String, IntWritable>();
        map.put("1", new IntWritable(33));
        map.put("2", new IntWritable(3));
        map.put("3", new IntWritable(53));
        map.put("4", new IntWritable(23));
        map.values().iterator();
        
        Iterator<IntWritable> itr = map.values().iterator();
        int length=0;
        while(itr.hasNext()){
            itr.next();
            length++;
        }
       int[] array =  new int[length];
       Iterator<IntWritable> itr2 = map.values().iterator();
       for(int i=0;itr2.hasNext();i++){
           IntWritable number= itr2.next();//取值
           int val = number.get();//转换类型
           array[i]=val;
       }
        int maxValue = NumberUtils.max(array);
        System.out.println(maxValue);
//        context.write(year, new IntWritable(maxValue));
    }

}
