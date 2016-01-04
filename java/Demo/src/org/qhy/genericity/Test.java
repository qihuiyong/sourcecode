
package org.qhy.genericity;


public class Test {

    /**
     * Description: 
     *
     * @param args
     */
    public static void main(String[] args) {
        GenericityClazz<Integer> gc= new GenericityClazz<Integer>(34, 55);
//        System.out.println(gc.getT1());
//        System.out.println(gc.getClazz(89));
        
        GenericityClazz<Integer> sub = new SubGenericity(new Object(), new Object());
        
        System.out.println(sub.getT1());
        System.out.println(sub.getClazz(89));
    }

}
