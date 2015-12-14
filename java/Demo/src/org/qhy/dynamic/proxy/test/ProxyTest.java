package org.qhy.dynamic.proxy.test;

import java.lang.reflect.Proxy;

import org.qhy.dynamic.proxy.BizDemoImpl;
import org.qhy.dynamic.proxy.IBizDemo;
import org.qhy.dynamic.proxy.handler.DynamicHandler;

public class ProxyTest {

	public static void main(String[] args) {
		BizDemoImpl demoImpl =new BizDemoImpl();
		Object proxyInstance = Proxy.newProxyInstance(demoImpl.getClass().getClassLoader(), new Class[]{IBizDemo.class}, new DynamicHandler(demoImpl));
		IBizDemo bizDemo = (IBizDemo)proxyInstance;
		String result = bizDemo.helloWord("ηχηχ»Τ");
	}

}
