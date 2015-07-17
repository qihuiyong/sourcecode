
package org.qhy.test;

import redis.clients.jedis.Jedis;


public class RedisTest {

    public static void main(String[] args) {
        Jedis jedis = new Jedis("192.168.199.142",6379);
        System.out.println(jedis.get("name"));
        jedis.close();
    }

}
