package org.qhy.hbasetest;

import java.io.IOException;
import java.util.Date;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.KeyValue;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.util.Bytes;

public class HbaseClient {

    public static void main(String[] args) throws IOException {
        Configuration conf = HBaseConfiguration.create();
//        conf.set("hbase.zookeeper.property.clientPort", "2181");  
//        conf.set("hbase.zookeeper.quorum", "hdp02dn");  
//        conf.set("hbase.master", "hdp02dn:600000");  
        Get get = new Get(Bytes.toBytes("1"));
        System.out.println(new Date());
        HTable table = new HTable(conf, Bytes.toBytes("mytable"));
        Result result = table.get(get);
        for (KeyValue kv : result.list()) {
            System.out.println("family:" + Bytes.toString(kv.getFamily()));
            System.out
                    .println("qualifier:" + Bytes.toString(kv.getQualifier()));
            System.out.println("value:" + Bytes.toString(kv.getValue()));
            System.out.println("Timestamp:" + kv.getTimestamp());
            System.out.println("-------------------------------------------");
        }
        table.close();
    }
}
