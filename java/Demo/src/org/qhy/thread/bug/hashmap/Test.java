
package org.qhy.thread.bug.hashmap;

import java.util.HashMap;


public class Test {
    private static final HashMap<Integer, Integer> map = new HashMap<Integer, Integer>(3,0.75f);
    public static void main(String[] args) {
       final int total =5000;
        new Thread("qhyThread--11111111111111"){
            @Override
            public void run() {
                int i=1;
                while(true){
                    map.put(i++, 1);
                    if(i==total){
                        break;
                    }
                }
                
            }
        }.start();
        
        new Thread("qhyThread--22222222"){
            @Override
            public void run() {
                int i=1;
                while(true){
                    map.put(i++, i);
                    if(i==total){
                        break;
                    }
                }
            }
        }.start();
        
        new Thread("qhyThread--22222222"){
            @Override
            public void run() {
                int i=1;
                while(true){
                    map.put(i++, i);
                    if(i==total){
                        break;
                    }
                }
            }
        }.start();
        
        
        new Thread("qhyThread--22222222"){
            @Override
            public void run() {
                int i=1;
                while(true){
                    map.put(i++, i);
                    if(i==total){
                        break;
                    }
                }
            }
        }.start();
        
        
        new Thread("qhyThread--22222222"){
            @Override
            public void run() {
                int i=1;
                while(true){
                    map.put(i++, i);
                    if(i==total){
                        break;
                    }
                }
            }
        }.start();
        
        
        new Thread("qhyThread--22222222"){
            @Override
            public void run() {
                int i=1;
                while(true){
                    map.put(i++, i);
                    if(i==total){
                        break;
                    }
                }
            }
        }.start();
        
        new Thread("qhyThread--22222222"){
            @Override
            public void run() {
                int i=1;
                while(true){
                    map.put(i++, i);
                    if(i==total){
                        break;
                    }
                }
            }
        }.start();
        
        new Thread("qhyThread--22222222"){
            @Override
            public void run() {
                int i=1;
                while(true){
                   System.out.println( map.get(i++));
                    if(i==total){
                        break;
                    }
                }
            }
        }.start();
        
    }
}
