
package org.qhy.demo.dubboclient;

import java.io.IOException;
import java.sql.Date;

import org.qhy.demo.dubboserver.ITestService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ClientMainTest {

    public static void main(String[] args) throws IOException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
            new String[] { "serviceClient.xml" });
        context.start();
        ITestService demoServer = (ITestService) context.getBean("demoService");
        
        demoServer.sayHi("戚辉永444");
//        System.out.println("按任意键退出");
//        System.in.read();
    }
}
