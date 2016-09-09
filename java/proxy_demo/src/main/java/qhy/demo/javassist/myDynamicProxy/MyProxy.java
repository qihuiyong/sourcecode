package qhy.demo.javassist.myDynamicProxy;

import java.lang.reflect.Modifier;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtField;
import javassist.CtField.Initializer;
import javassist.CtMethod;

public class MyProxy {
	private static int proxyIndex = 1;
	private static String PROXY_SUFFIX="_$Proxy_"; //代理类的名称
	/**
	 * 
	 * Description: 创建代理对象. <br/>
	 * @param interfaceClass 接口定义
	 * @param implClass 实现类
	 * @param invocationClass 代理类
	 * @return
	 * @throws Exception 
	 */
	public static <T> T newProxyInstance(Class<T> interfaceClass,
			Class<? extends T> implClass,
			Class<? extends MyInvocationHandler> invocationClass)
			throws Exception {
		String interfaceName = interfaceClass.getName(); //业务接口
		String implClassName = implClass.getName();//业务实现类，被代理的对象
		
		String invocationImplClassName = invocationClass.getName(); //代理模式接口实现类
		//开始生成代理类实例
		String proxyClassName = interfaceName + PROXY_SUFFIX + proxyIndex++;
		ClassPool cp = ClassPool.getDefault();
		
		CtClass proxyImplClass = cp.makeClass(proxyClassName);
		
		//1、设置实现类的 接口
		CtClass interfaceCtClass = cp.getCtClass(interfaceName);
		proxyImplClass.addInterface(interfaceCtClass);
		
		
		CtMethod[] interfaceCtMethods = interfaceCtClass.getDeclaredMethods();
		if(interfaceCtMethods !=null && interfaceCtMethods.length > 0){
			//2、注入invocationHandler 属性
			CtField newField = new CtField(cp.get(invocationImplClassName), "testInvoke", proxyImplClass);
			newField.setModifiers(Modifier.PRIVATE);
			proxyImplClass.addField(newField, Initializer.byExpr(" testInvoke = new "+invocationImplClassName+"();"));
//			proxyImplClass.addField(newField, Initializer.byNew(cp.get(invocationImplClassName)));
			
			for (CtMethod ctMethod : interfaceCtMethods) {
				//3、循环创建接口的实现方法
				//3.1、创建方法定义
				String methodName = ctMethod.getName();
				CtClass returnType = ctMethod.getReturnType();
				CtClass[] paramTypes = ctMethod.getParameterTypes();
				CtMethod newMethod = new CtMethod(returnType, methodName, paramTypes, proxyImplClass);
				newMethod.setModifiers(Modifier.PUBLIC);

				//3.1、创建方法体
				StringBuffer body = new StringBuffer();
				body.append("{");
				//3.2、生成获取指定实现类方法的代码
				String getMethodArg = generateImplGetMethodCode(methodName,implClassName,paramTypes);
				
				body.append("\n Object result = testInvoke.invoke(Class.forName(\""+implClassName+"\").newInstance(),"+getMethodArg+",$args);");
				
				//3.3、生成获取指定实现类方法的代码
				String returnCode = generateImplReturnCode(returnType,"result");
				if(returnCode !=null && returnCode.trim().length()>5){
					body.append(returnCode);
				}
//				body.append("\n return ("+returnType.getName()+")result;");
				
				body.append("}");
				newMethod.setBody(body.toString());
				proxyImplClass.addMethod(newMethod);
			}
		}
		Object obj = proxyImplClass.toClass().newInstance();
		
		return (T)obj;
	}
	/**
	 * Description:生成返回代码 . <br/>
	 * @param returnType
	 * @param string
	 * @return
	 */
	private static String generateImplReturnCode(CtClass returnType,
			String returnVariableName) {
		String returnCode = null;
		if(returnType != CtClass.voidType){
			if(returnType.isPrimitive()){
				if(returnType == CtClass.intType){
					returnCode = "\n return  (("+Integer.class.getName()+")"+returnVariableName+").intValue();";
				}else if(returnType == CtClass.byteType){
					returnCode = "\n return  (("+Byte.class.getName()+")"+returnVariableName+").byteValue();";
				}else if(returnType == CtClass.booleanType){
					returnCode = "\n return  (("+Boolean.class.getName()+")"+returnVariableName+").booleanValue();";
				}else if(returnType == CtClass.charType){
					returnCode = "\n return  (("+Character.class.getName()+")"+returnVariableName+").charValue();";
				}else if(returnType == CtClass.doubleType){
					returnCode = "\n return  (("+Double.class.getName()+")"+returnVariableName+").doubleValue();";
				}else if(returnType == CtClass.floatType){
					returnCode = "\n return  (("+Float.class.getName()+")"+returnVariableName+").floatValue();";
				}else if(returnType == CtClass.longType){
					returnCode = "\n return  (("+Long.class.getName()+")"+returnVariableName+").longValue();";
				}else if(returnType == CtClass.shortType){
					returnCode = "\n return  (("+Short.class.getName()+")"+returnVariableName+").shortValue();";
				}
			}else{
				returnCode = "\n return ("+returnType.getName()+") "+returnVariableName+";";
			}
		}
		return returnCode;
	}
	/**
	 * Description: 获取方法的代码指定实现方法的代码. <br/>
	 * @param methodName
	 * @param implClassName
	 * @param paramTypes
	 */
	private static String generateImplGetMethodCode(String methodName,
			String implClassName, CtClass[] paramTypes) {
		
		//没用约束的方法这么定义获取方式
		String getMethodArg="Class.forName(\""+implClassName+"\").getDeclaredMethod(\""+methodName+"\", null)";
		if(paramTypes!=null && paramTypes.length>0){
			StringBuffer sb = new StringBuffer("new Class[]{");
			int paramLength = paramTypes.length;
			for (int j = 0; j < paramLength; j++) {
				String calssName = paramTypes[j].getName();
				if(j == (paramLength-1)){
					sb.append(calssName).append(".class");
				}else{
					sb.append(calssName).append(".class").append(",");
				}
			}
			sb.append("}");
			getMethodArg = "Class.forName(\""+implClassName+"\").getDeclaredMethod(\""+methodName+"\","+sb.toString()+")";
		}
		return getMethodArg;
	}
	
}
