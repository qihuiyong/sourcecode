
package org.qhy.test;

import java.util.Random;

import org.qhy.test.model.Data;

public class ThreadDemo {

    private final static ThreadLocal<Data> datas = new ThreadLocal<Data>();

    public Data putData() {
        Data data = new Data();
        data.setId(Thread.currentThread().getId()+"");
        data.setName(Thread.currentThread().getName());
        Random r = new Random();
        data.setAge(r.nextInt(100));
        datas.set(data);
        return data;
    }

    public Data getData() {
        Data data = datas.get();
        if (data == null) {
            data = new Data();
            data.setName(Thread.currentThread().getName());
            Random r = new Random();
            data.setAge(r.nextInt(100));
            datas.set(data);
        }
        return data;
    }
}
