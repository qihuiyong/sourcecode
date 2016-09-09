package qhy.demo.javassist.myDynamicProxy.test;

import java.util.Date;

public class EchoEn implements IEcho {
	@Override
	public void echo() {
		System.out.println("hello "+233434+"!");
	}
	@Override
	public long getCurrentType() {
		System.out.println("getCurrentType");
		return System.currentTimeMillis();
	}

	@Override
	public Boolean echo1(int aa, String user) {
		System.out.println("getDate;param="+aa+","+user);
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public char getChar() {
		return 'A';
	}

	@Override
	public Date getDate(String aa) {
		System.out.println("getDate;param="+aa);
		
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public ObjectModel echo(ObjectModel model) {
		System.out.println("echo(ObjectModel model)>>>>>>>param="+model.getName());
		ObjectModel result = new ObjectModel("郑文玉", 33, 10023L);
		return result;
	}

	@Override
	public int echo(int aa) {
		
		System.out.println("我是AAA"+aa+"!");
		return -98;
	}


}

