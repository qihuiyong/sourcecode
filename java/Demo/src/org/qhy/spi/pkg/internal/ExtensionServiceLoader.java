package org.qhy.spi.pkg.internal;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Enumeration;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.qhy.spi.pkg.anonation.SPI;

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
	private final ConcurrentMap<String, Class<?>> extensionClassesMap = new ConcurrentHashMap<String, Class<?>>();
	private Class<?> type;

	/**
	 * 创建一个扩展接口加载器，
	 * @param extensionType
	 * @return
	 * @throws Exception 
	 */
	public static <T> ExtensionServiceLoader<T> getServiceLoader(Class<?> extensionType) throws Exception {
		if(extensionType == null){
			throw new IllegalArgumentException("extensionType is null!");
		}
		if(!extensionType.isAnnotationPresent(SPI.class)){
			throw new IllegalArgumentException("extensionType ("+extensionType+")Invalid extension,because: No annotations (@"+SPI.class.getSimpleName()+")!");
		}
		ExtensionServiceLoader<T> serviceLoader = (ExtensionServiceLoader<T>) loaderMap.get(extensionType);
		if(serviceLoader == null){
			try {
				serviceLoader = new ExtensionServiceLoader<T>(extensionType);
			} catch (Exception e) {
				throw e;
			}
			loaderMap.put(extensionType, serviceLoader);
		}
		return serviceLoader;
	}
	public T getServiceInstance(String name) throws Exception{
		if(name == null || name.trim().length()==0){
			throw new IllegalArgumentException("name is null!");
		}
		T t = (T)instanceMap.get(name);
		if(t == null){
			Class<?> clazz = extensionClassesMap.get(name);
			if(clazz == null){
				throw new IllegalArgumentException("name:["+name+"] not defination in file("+SERVICES_DIRECTORY+type.getName()+")");
			}
			try {
				t = (T)clazz.newInstance();
			} catch (Exception e) {
				throw e;
			} 
			instanceMap.putIfAbsent(name, t);
		}
		return t;
	}
	public T getDefaultInstance() throws Exception{
		SPI spi = type.getAnnotation(SPI.class);
		return this.getServiceInstance(spi.value());
	}

	private ExtensionServiceLoader(Class<?> extensionType) throws IllegalStateException, ClassNotFoundException, IOException {
		this.type = extensionType;
		this.loadExtensionClasses(extensionType);
	}
	private void loadExtensionClasses(Class<?> extensionType) throws ClassNotFoundException, IOException,IllegalStateException{
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
						
						//转换为class
						String name  = defArray[0],className=defArray[1];
						Class<?>  clazz =Class.forName(className,true,classLoader);
						
						if (!type.isAssignableFrom(clazz)) {
                            throw new IllegalStateException("class line defination [" + className + "] not an subType of("+type.getName()+")");
                        }
						extensionClassesMap.put(name,clazz);
					}
				}finally{
					reader.close();
				}
				
			}
        }
	}
}
