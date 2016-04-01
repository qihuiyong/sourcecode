
package org.qhy.thread;


public class Myboject {
    Object o = new Object();
    public synchronized void m1() throws InterruptedException {
            System.out.println("m1 start");
//            Thread.currentThread().wait(1000*8);
            this.wait();
            System.out.println("m1 end");
        
        
    }
    
    public synchronized void m2() throws InterruptedException {
            System.out.println("m2 start");
            System.out.println("m2 end");
            Thread.currentThread().sleep(5000);
            System.out.println("notify called");
            this.notify();
            
    }
}
