
package org.qhy.oom;

import java.lang.reflect.Method;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

public class PermSizeTest {

    /**
     * 
     * Description: 自定义被拦截的方法
     *
     */
    public void helloworld(){
        System.out.println("hello world!");
    }
    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        while (true) {
            Enhancer eh = new Enhancer();
            eh.setSuperclass(PermSizeTest.class);
            eh.setUseCache(false);
            eh.setCallback(new MethodInterceptor() {
                public Object intercept(Object obj, Method method, Object[] objArray, MethodProxy proxy) throws Throwable {
                    String methodName = method.getName();
                    if(methodName.equals("helloworld")){
                        System.out.println("拦截的方法名:>"+method.getName());
                    }
                    Object result =  proxy.invokeSuper(obj, objArray);
                    if(methodName.equals("helloworld")){
                        System.out.println("调用结束后"+method.getName());
                    }
                    return result;
                }
            });
            PermSizeTest oom =(PermSizeTest) eh.create();
            oom.helloworld();
        }
    }
}
