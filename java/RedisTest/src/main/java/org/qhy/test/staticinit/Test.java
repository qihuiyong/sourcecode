
package org.qhy.test.staticinit;


class SuperClass{
    static{
        System.out.println("super class init.");
    }
    public final static String value=System.currentTimeMillis()+"";
}

class SubClass extends SuperClass{
    static{
        System.out.println("sub class init.");
    }
}

public class Test{
    public static String value=System.currentTimeMillis()+"";
    public static void main(String[]args) throws InterruptedException{
        long start = System.currentTimeMillis();
        System.out.println(SuperClass.value);
//        Class c = SubClass.class;
//        Thread.sleep(5000);
//        System.out.println(SubClass.value);
//        System.out.println(Test.value);
        
    }
    
}
