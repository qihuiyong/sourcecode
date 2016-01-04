package org.qhy.dynamic.proxy.handler;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class DynamicHandler implements InvocationHandler {

	private Object proxyie;
	public DynamicHandler(Object proxyie){
		this.proxyie = proxyie;
	}
	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
	    System.out.println("33333333");
		return method.invoke(proxyie, args);
	}

}
