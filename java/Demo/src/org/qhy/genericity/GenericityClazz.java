
package org.qhy.genericity;

import java.util.List;


public class GenericityClazz<T> {
    private T t1;
    private T t2;
    
    
    public <U> U getClazz(U u){
     return u;   
    }
    
    
    
    
    
    
    
    
    
    public GenericityClazz(T t1,T t2) {
        this.t1=t1;
        this.t2=t2;
    }
    public T getT1() {
        return t1;
    }
    public void setT1(T t1) {
        this.t1 = t1;
    }
    public T getT2() {
        return t2;
    }
    public void setT2(T t2) {
        this.t2 = t2;
    }
    
}
