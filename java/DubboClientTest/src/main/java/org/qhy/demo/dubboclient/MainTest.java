
package org.qhy.demo.dubboclient;

import java.io.IOException;
import java.sql.Date;

import org.qhy.demo.dubboserver.ITestService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainTest {

    public static void main(String[] args) throws IOException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
            new String[] { "serviceClient.xml" });
        context.start();
        ITestService demoServer = (ITestService) context.getBean("demoService");
        
        demoServer.sayHi("333333333333333......");
    }
}
