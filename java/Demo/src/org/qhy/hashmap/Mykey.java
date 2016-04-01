
package org.qhy.hashmap;


public class Mykey {
    private String keyVal;
    
    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Mykey){
            Mykey mk = (Mykey)obj;
            return this.getKeyVal().equals(mk.getKeyVal());
        }
         return false;
    }
    @Override
    public int hashCode() {
        return this.getKeyVal().hashCode();
//        return super.hashCode();
    }
    public String getKeyVal() {
        return keyVal;
    }
    public void setKeyVal(String keyVal) {
        this.keyVal = keyVal;
    }
    
}
