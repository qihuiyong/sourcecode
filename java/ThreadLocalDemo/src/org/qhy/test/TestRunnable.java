
package org.qhy.test;

import java.util.Random;

import org.qhy.test.model.Data;

public class TestRunnable implements Runnable {

    ThreadDemo td = new ThreadDemo();

    /**
     * Description:
     * 
     * @param args
     */
    public static void main(String[] args) {
        TestRunnable tr = new TestRunnable();

        for (int i = 0; i < 10; i++) {

            Thread thread1 = new Thread(tr, "T---1111111");
            Thread thread2 = new Thread(tr, "T---2222222");
            Thread thread3 = new Thread(tr, "T---3333333");
            thread1.start();
            thread2.start();
            thread3.start();
        }

    }

    @Override
    public void run() {
        td.putData();
        Data data = td.getData();
        System.out.println(data.getId()+"\t\t"+data.getName() + "--->" + data.getAge());
    }

}
