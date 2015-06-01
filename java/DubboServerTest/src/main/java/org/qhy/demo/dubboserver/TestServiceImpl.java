
package org.qhy.demo.dubboserver;


public class TestServiceImpl implements ITestService {

    public void sayHi(String name) {
        System.out.println("hello:"+name);
    }
}
