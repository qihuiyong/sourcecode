package qhy.demo.javassist.myDynamicProxy.test;

import java.util.Date;

public interface IEcho {
	public void echo();
	public int echo(int aa);
	public Boolean echo1(int aa,String user);
	public long getCurrentType();
	public Date getDate(String aa);
	public ObjectModel echo(ObjectModel model);
	public char getChar();
}
