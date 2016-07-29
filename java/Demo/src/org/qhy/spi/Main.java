package org.qhy.spi;

import java.util.Iterator;
import java.util.ServiceLoader;

import org.qhy.spi.api.IEcho;

public class Main {

	public static void main(String[] args) {
		ServiceLoader<IEcho> echos = ServiceLoader.load(IEcho.class);
		Iterator<IEcho> itr = echos.iterator();
		System.out.println("java.class.path>>"+System.getProperty("java.class.path"));
		while(itr.hasNext()){
			IEcho echo = itr.next();
			echo.echo();
		}

	}

}

