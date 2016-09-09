package qhy.demo.javassist.myDynamicProxy.test;

import qhy.demo.javassist.myDynamicProxy.MyProxy;
public class Test {

	/**
	 * Description: . <br/>
	 * 
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		IEcho echo = MyProxy.newProxyInstance(IEcho.class, EchoEn.class, TestInvocationHandler.class);
		echo.echo();
		int aa = echo.echo(888);
		System.err.println(">>>>>>>>>>>>>>"+aa);
		
		Boolean bool = echo.echo1(555, "qihyyy");
		System.err.println(">>>>>>>>>>>>>>"+bool);
		
		ObjectModel result = new ObjectModel("qiqi玉", 31, 6013L);
		ObjectModel objModel = echo.echo(result);
		System.err.println(">>>>objModel>>>>>>>>>>"+objModel.getName()+"--"+objModel.getAge()+"--"+objModel.getCurrentTime());
		
		
		char cha = echo.getChar();
		System.err.println(">>>>getChar>>>>>>>>>>"+cha);
	}
}
