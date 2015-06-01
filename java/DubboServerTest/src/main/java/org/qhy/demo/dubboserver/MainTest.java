
package org.qhy.demo.dubboserver;

import java.io.IOException;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainTest {

    public static void main(String[] args) throws IOException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
            new String[] { "serviceProvider.xml" });
        System.out.println("11111111");
        context.start();
        System.out.println("按任意键退出");
        System.in.read();

    }
}
