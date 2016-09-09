package org.qhy.spi;

import org.qhy.spi.api.IEcho;
import org.qhy.spi.pkg.internal.ExtensionServiceLoader;


public class Test {
	
	public static void main(String[] args) throws Exception {
		ExtensionServiceLoader<IEcho> serviceLoader = ExtensionServiceLoader.getServiceLoader(IEcho.class);
		IEcho echo2 =serviceLoader.getServiceInstance("echo2");
		IEcho defaultEcho =serviceLoader.getDefaultInstance();
		echo2.echo();
		defaultEcho.echo();
	}
}

