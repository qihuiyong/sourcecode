
package org.qhy.hashmap;

import java.util.HashMap;
import java.util.Map;


public class Test {

    /**
     * Description: 
     *
     * @param args
     */
    public static void main(String[] args) {
        Mykey k1 = new Mykey();
        k1.setKeyVal("233");
        Map<Mykey, Integer> map = new HashMap<Mykey, Integer>();
        map.put(k1, 4545);
        Mykey k2 = new Mykey();
        k2.setKeyVal("233");
        System.out.println(map.get(k2));
    }

}
