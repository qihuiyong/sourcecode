package org.qhy.dynamic.proxy;

public class BizDemoImpl implements IBizDemo{

	@Override
	public String helloWord(String name){
		System.out.println("hi '"+name);
		return name+"OOPP";
	}

}
