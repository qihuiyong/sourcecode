package org.qhy.proxy;

import java.lang.reflect.Proxy;

/**
 * ClassName: org.qhy.proxy.Test <br/>
 * Description: TODO 功能描述. <br/>
 * date: 2016年8月31日 上午11:57:50 <br/>
 * Company: gome
 * 
 * @author qihuiyong-ds
 * @version
 */
public class Test {

	/**
	 * Description: . <br/>
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
			IEcho echo = (IEcho)Proxy.newProxyInstance(
					Test.class.getClassLoader(), new Class[]{IEcho.class}, new MyHandller(new EchoCn()));
			String result = echo.echo("戚辉永");
			System.out.println(result);
	}
}
