
package org.qhy.test;

import java.util.Set;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;


public class RedisTest {
    public static String host ="192.168.199.147";
    public static int port = 6379;
    public static void main(String[] args) {
//        keysQuery_DEL();
//        transactionDemo();
        queryAll("tx*");
//        deleteAll("tx*");
    }
    
    /**
     * 事务demo
     * Description: 
     *
     */
    public static void transactionDemo() {
        Jedis jedis = new Jedis(host,port);
        Transaction transaction = jedis.multi();
        int count = 0;
        while(true){
            transaction.set("tx"+count, "戚辉永"+count);
            System.out.println("tx"+count+">>>>>戚辉永"+count);
            if(count > 10000*10){
                break;
            }else{
                count++;
            }
            
        }
        boolean successful=false;
        if(successful){
            transaction.exec(); //提交事务
        }else{
            transaction.discard();//回滚事务
        }
        jedis.close();
    }
    
    public static void keysQuery_DEL () {
        Jedis jedis = new Jedis(host,port);
//        System.out.println(jedis.get("name"));
        //添加数据
        jedis.set("13487467487", "testD地方的发生法第三方的45dfd三方第三方dfddfdf");
        jedis.set("13487467486", "testD地方的发生法第三方的第三sd方第三方dfddfdf");
        jedis.set("13487467482", "testD地方的发生法第三方的第三fff方第三方dfddfdf");
        jedis.set("13487467483", "testD地方的发生法第三方的第三ggfg4方第三方dfddfdf");
        jedis.append("13487467484", "testD地方的发生法第三方的第三ffg方第三方dfddfdf");//追加
        //前缀查找keys
        Set<String> keySet = jedis.keys("13*");
        for (String key : keySet) {
            System.out.println(key+">>>>>"+jedis.get(key));
           
        }
        //批量删除
       String[] str= keySet.toArray(new String[0]);
        jedis.del(str);
        jedis.close();
    }
    public static void queryAll(String patten) {
        Jedis jedis = new Jedis(host,port);
        //前缀查找keys
        Set<String> keySet = jedis.keys(patten);
        for (String key : keySet) {
            System.out.println(key+">>kkkkkkkkvvvvvvvvvvvv>>>"+jedis.get(key));
        }
        jedis.close();
    }
    
    public static void deleteAll(String patten) {
        Jedis jedis = new Jedis(host,port);
        //前缀查找keys
        Set<String> keySet = jedis.keys(patten);
        String[] keys = keySet.toArray(new String[0]);
        if(keys != null && keys.length > 0){
            jedis.del(keys);
        }
        jedis.close();
    }
}
