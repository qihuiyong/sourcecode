package qhy.demo.jdk_proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class MyHandller implements InvocationHandler {
	private IEcho echo;
	
	public MyHandller(IEcho ec) {
		this.echo= ec;
	}
	
	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		if(echo instanceof EchoEn){
			System.out.println("i proxy english!!");
		}else if(echo instanceof EchoCn){
			System.out.println("我代理中文!!");
		}
		return method.invoke(echo, args);
//		return "csdd";
	}

}

