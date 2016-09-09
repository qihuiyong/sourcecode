package qhy.demo.javassist.myDynamicProxy.test;

import java.lang.reflect.Method;

import qhy.demo.javassist.myDynamicProxy.MyInvocationHandler;
public class TestInvocationHandler implements MyInvocationHandler {
	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		System.out.println("执行前：拦截111111111111111");
		Object result =  method.invoke(proxy, args);
		System.out.println("执行后：拦截22222222222222222");
		return result;
	}
}

