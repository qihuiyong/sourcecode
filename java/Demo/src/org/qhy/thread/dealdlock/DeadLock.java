
package org.qhy.thread.dealdlock;

import java.util.concurrent.TimeUnit;


public class DeadLock {

    private Object leftLock = new Object();
    private Object rightLock = new Object();

    public void leftRight() {
        synchronized (leftLock) {
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            synchronized (rightLock) {
                System.out.println("leftRight");
            }
        }
    }

    public void rightLeft() {
        synchronized (rightLock) {
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            synchronized (leftLock) {
                System.out.println("leftRight");
            }
        }
    }

    public static void main(String[] args) {
        final DeadLock deadLock = new DeadLock();

        Thread t1 = new Thread(new Runnable() {

            @Override
            public void run() {
                deadLock.leftRight();
            }
        });
        Thread t2 = new Thread(new Runnable() {

            @Override
            public void run() {
                deadLock.rightLeft();
            }
        });

        t1.start();
        t2.start();
    }
}