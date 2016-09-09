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
 * ClassName: org.qhy.spi.pkg.internal.ExtensionServiceLoader <br/>
 * Description: spi服务接口加载类实现. <br/>
 */
public class ExtensionServiceLoader<T> {
	private static final String SERVICES_DIRECTORY = "META-INF"+File.separator+"services"+File.separator;

	
	//存放不同类型的loader
	private static final ConcurrentMap<Class<?>, ExtensionServiceLoader<?>> loaderMap = new ConcurrentHashMap<Class<?>, ExtensionServiceLoader<?>>();

	//存放不同实例
	private final ConcurrentMap<String, Object> instanceMap = new ConcurrentHashMap<String, Object>();
	//加载不同的所有的实现的类定义
	private final ConcurrentMap<String, Class<?>> extensionClassesMap = new ConcurrentHashMap<String, Class<?>>();
	private Class<?> type;

	/**
	 * Description: . <br/>
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
		//从map中获取
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
		//从map中取实例如果取不到 就创建病存放到map中
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
		this.type = extensionType;//该loader对应的类型-一接口一loader
		this.loadExtensionClasses(extensionType);
	}
	/**
	 * 
	 * Description: 加载不同的类定义. <br/>
	 * @param extensionType 扩展类型
	 * @throws ClassNotFoundException
	 * @throws IOException
	 * @throws IllegalStateException
	 */
	private void loadExtensionClasses(Class<?> extensionType) throws ClassNotFoundException, IOException,IllegalStateException{
		Enumeration<java.net.URL> urls;
		//文件名 也就是接口名
		String fileName = SERVICES_DIRECTORY+extensionType.getName();
		ClassLoader classLoader = ExtensionServiceLoader.class.getClassLoader();
		 if (classLoader != null) {
             urls = classLoader.getResources(fileName);
         } else {
             urls = ClassLoader.getSystemResources(fileName);
             classLoader = ClassLoader.getSystemClassLoader();
         }
        if (urls != null && urls.hasMoreElements()) {
			while (urls.hasMoreElements()) {
				java.net.URL url = urls.nextElement();
				BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream(), "utf-8"));
				String line = null;
				try{
					while ((line = reader.readLine()) != null) {
						if(line == null || line.trim().length()==0 || line.contains("#")){
							continue;
						}
						//读取文件一行定义并处理
						line =line.trim();
						String[] defArray = line.split("=");
						if(defArray.length != 2){
							continue;
						}
						//拆分文件，大一部分是名字，第二部分是类全名
						String name  = defArray[0],className=defArray[1];
						Class<?>  clazz =Class.forName(className,true,classLoader);
						//判断文件定义的类，是不是加载接口的子类
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
