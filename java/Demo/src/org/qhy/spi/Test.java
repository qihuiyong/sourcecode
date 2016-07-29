package org.qhy.spi;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Enumeration;

public class Test {

	public static void main(String[] args) throws Exception {
		ClassLoader classLoader = Test.class.getClassLoader();
		Enumeration<URL>  urls = classLoader.getResources("META-INF\\services\\org.qhy.spi.api.IEcho");
//		Enumeration<URL>  urls = ClassLoader.getSystemResources("META-INF/services/org.qhy.spi.api.IEcho");
        if (urls != null) {
			while (urls.hasMoreElements()) {
				java.net.URL url = urls.nextElement();
				BufferedReader reader = new BufferedReader(
						new InputStreamReader(url.openStream(), "utf-8"));
				String line = null;
				while ((line = reader.readLine()) != null) {
					final int ci = line.indexOf('#');
					if (ci >= 0)
						line = line.substring(0, ci);
                    line = line.trim();
                    System.out.println("line>>>"+line);
				}
			}
        }

	}

}

