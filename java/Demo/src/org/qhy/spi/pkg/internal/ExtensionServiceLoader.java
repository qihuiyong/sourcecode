package org.qhy.spi.pkg.internal;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.Enumeration;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * 加载服务实例
 * 
 * @author qihuiyong private static final ConcurrentMap<Class<?>,
 *         ExtensionLoader<?>> EXTENSION_LOADERS = new
 *         ConcurrentHashMap<Class<?>, ExtensionLoader<?>>(); <T>
 *         ExtensionLoader<T>
 */
public class ExtensionServiceLoader<T> {
	private static final String SERVICES_DIRECTORY = "META-INF"+File.separator+"services"+File.separator;
	private static final ConcurrentMap<Class<?>, ExtensionServiceLoader<?>> loaderMap = new ConcurrentHashMap<Class<?>, ExtensionServiceLoader<?>>();
	private final ConcurrentMap<String, Object> instanceMap = new ConcurrentHashMap<String, Object>();
	private final ConcurrentMap<String, String> extensionDefMap = new ConcurrentHashMap<String, String>();
	private Class<?> type;

	public static <T> ExtensionServiceLoader<T> getServiceLoader(Class<?> extensionType) {
		ExtensionServiceLoader<T> serviceLoader = (ExtensionServiceLoader<T>) loaderMap.get(extensionType);
		if(serviceLoader == null){
			try {
				serviceLoader = new ExtensionServiceLoader<T>(extensionType);
			} catch (Exception e) {
				e.printStackTrace();
			}
			loaderMap.put(extensionType, serviceLoader);
		}
		return serviceLoader;
	}
	public T getServiceInstance(String name) throws Exception{
		T t = (T)instanceMap.get(name);
		if(t == null){
			String classDef = extensionDefMap.get(name);
			ClassLoader classLoader = ExtensionServiceLoader.class.getClassLoader();
			t = (T)Class.forName(classDef,true,classLoader).newInstance();
			instanceMap.putIfAbsent(name, t);
		}
		return t;
	}

	private ExtensionServiceLoader(Class<?> extensionType) throws Exception {
		this.type = extensionType;
		this.loadExtensionDef(extensionType);
	}
	private void loadExtensionDef(Class<?> extensionType) throws Exception{
		Enumeration<java.net.URL> urls;
		String fileName = SERVICES_DIRECTORY+extensionType.getName();
		ClassLoader classLoader = ExtensionServiceLoader.class.getClassLoader();
		 if (classLoader != null) {
             urls = classLoader.getResources(fileName);
         } else {
             urls = ClassLoader.getSystemResources(fileName);
         }
        if (urls != null && urls.hasMoreElements()) {
			while (urls.hasMoreElements()) {
				java.net.URL url = urls.nextElement();
				BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream(), "utf-8"));
				String line = null;
				try{
					while ((line = reader.readLine()) != null) {
						//空行，包含#的注视行排除掉
						if(line == null || line.trim().length()==0 || line.contains("#")){
							continue;
						}
						line =line.trim();
						String[] defArray = line.split("=");
						//格式不成缺排除掉 正确格式 xxName=com.qh.xx.xxImpl
						if(defArray.length != 2){
							continue;
						}
						extensionDefMap.put(defArray[0], defArray[1]);
					}
				}finally{
					reader.close();
				}
				
			}
        }
	}
}
