package qhy.demo.javassist;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import qhy.demo.javassist.myDynamicProxy.test.EchoEn;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtConstructor;
import javassist.CtField;
import javassist.CtNewMethod;

/**
 * ClassName: qhy.demo.javassist.Test <br/>
 * Description: TODO 功能描述. <br/>
 * date: 2016年8月31日 下午5:38:41 <br/>
 * Company: gome
 * @author qihuiyong-ds
 * @version 
 */
public class Test {
	public static void main(String[] args) throws Exception {
//		createClass();
		aa();
	}
	private static void aa() throws Exception, NoSuchMethodException{
		Method m = Class.forName(EchoEn.class.getName()).getDeclaredMethod("echo", null);
		System.out.println(m);
	}
	/**
	 *   ClassPool cp=ClassPool.getDefault();  
        CtClass ctClass=cp.makeClass("com.slovef.JavassistClass");  
          
        StringBuffer body=null;  
        //参数  1：属性类型  2：属性名称  3：所属类CtClass  
        CtField ctField=new CtField(cp.get("java.lang.String"), "name", ctClass);  
        ctField.setModifiers(Modifier.PRIVATE);  
        //设置name属性的get set方法  
        ctClass.addMethod(CtNewMethod.setter("setName", ctField));  
        ctClass.addMethod(CtNewMethod.getter("getName", ctField));  
        ctClass.addField(ctField, Initializer.constant("default"));  
          
        //参数  1：参数类型   2：所属类CtClass  
        CtConstructor ctConstructor=new CtConstructor(new CtClass[]{}, ctClass);  
        body=new StringBuffer();  
        body.append("{\n name=\"me\";\n}");  
        ctConstructor.setBody(body.toString());  
        ctClass.addConstructor(ctConstructor);  
          
        //参数：  1：返回类型  2：方法名称  3：传入参数类型  4：所属类CtClass  
        CtMethod ctMethod=new CtMethod(CtClass.voidType,"execute",new CtClass[]{},ctClass);  
        ctMethod.setModifiers(Modifier.PUBLIC);  
        body=new StringBuffer();  
        body.append("{\n System.out.println(name);");  
        body.append("\n System.out.println(\"execute ok\");");  
        body.append("\n return ;");  
        body.append("\n}");  
        ctMethod.setBody(body.toString());  
        ctClass.addMethod(ctMethod);  
        Class<?> c=ctClass.toClass();  
        Object o=c.newInstance();  
        Method method=o.getClass().getMethod("execute", new Class[]{});  
        //调用字节码生成类的execute方法  
        method.invoke(o, new Object[]{});  
	 * Description:创建实例的测试代码 . <br/>
	 * @throws Exception 
	 */
	public static void createClass() throws Exception{
		System.out.println(int.class.getName());
		ClassPool cp = ClassPool.getDefault();
		CtClass ctClass = cp.makeClass("javassist.Class1");
		
		//添加字段 和 getter setter方法
		CtField field = new CtField(cp.get(String.class.getName()), "name", ctClass);
		field.setModifiers(Modifier.PRIVATE);
		ctClass.addField(field);
		ctClass.addMethod(CtNewMethod.getter("getName", field));
		ctClass.addMethod(CtNewMethod.setter("setName", field));
		
		CtField field2 = new CtField(cp.get(Integer.class.getName()), "age", ctClass);
		field2.setModifiers(Modifier.PRIVATE);
		ctClass.addField(field2);
		ctClass.addMethod(CtNewMethod.getter("getAge", field2));
		ctClass.addMethod(CtNewMethod.setter("setAge", field2));
		
		
		//添加带参数的构造函数
		CtConstructor constructor = new CtConstructor(new CtClass[]{cp.get(String.class.getName()),cp.get(Integer.class.getName())}, ctClass);
		constructor.setModifiers(Modifier.PUBLIC);
		StringBuffer body = new StringBuffer();
		body.append("{");
		body.append("this.setName($1);");
		body.append("this.setAge($2);");
		body.append("System.out.println(name+\"---初始化完毕-->\"+age);");
		body.append("}");
		
		ctClass.addConstructor(constructor);
		
		//调用方法
		Object obj = ctClass.toClass().newInstance();
		
		
		Method setNameMethod = obj.getClass().getMethod("setName", String.class);
		setNameMethod.invoke(obj, "1111111111");
		
		Method getNameMethod = obj.getClass().getMethod("getName", new Class[]{});
		Object result = getNameMethod.invoke(obj, new Object[]{});
		System.out.println(result);
		
	}
}

