package org.qhy.tree.treeMap;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * ClassName: org.qhy.tree.treeMap.MyTreeMap <br/>
 * Description: TODO 功能描述. <br/>
 * date: 2016年8月1日 下午1:52:13 <br/>
 * Company: gome
 * 
 * @author qihuiyong-ds
 * @version
 */
public class MyTreeMap<K, V> {
	private Entry<K, V> root;
	private List<Entry<K, V>> list =new ArrayList<MyTreeMap.Entry<K,V>>();
	public Entry<K, V> getRoot(){
		return root;
	}
	public List<Entry<K, V>> getAll(){
		return list;
	}
	public V put(K key, V value) {
		if(this.root == null){
			root = new Entry<K, V>(key, value,null);
			return null;
		}
		Entry<K, V> rootN = root;
		Entry<K, V> parent=null;
		int cpr = 0; 
		Comparable<? super K> k = (Comparable<? super K>) key;
		while (rootN != null){
			parent = rootN;
			cpr = k.compareTo(parent.key);
			if(cpr > 0){
				rootN = rightOf(rootN);
			}else if(cpr < 0){
				rootN = leftOf(rootN);
			}else{
				return rootN.setValue(value);
			}
		}
		Entry<K, V> newEntry = new Entry<K, V>(key, value,parent);
		if(cpr > 0){
			parent.right = newEntry;
		}else{
			parent.left = newEntry;
		}
		list.add(newEntry);
		rotateTree(newEntry);
		
		return null;
	}

	private void rotateTree(Entry<K, V>  newEntry){
		newEntry.color = RED;
		while(newEntry != null && newEntry != root && parentOf(newEntry).color == RED){
			Entry<K, V> p = parentOf(newEntry);
			Entry<K, V> p_p_leftEq = leftOf(parentOf(parentOf(newEntry)));
			
			
			
			
			if(p == p_p_leftEq){
				Entry<K, V> p_p_right = rightOf(parentOf(parentOf(newEntry)));
				if(colorOf(p_p_right) == RED){
					setColor(parentOf(newEntry),BLACK);
					setColor(p_p_right, BLACK);
					setColor(parentOf(parentOf(newEntry)), RED);
					newEntry = parentOf(parentOf(newEntry));
				}else{
					if(newEntry == rightOf(parentOf(newEntry))){
						newEntry = parentOf(newEntry);
						rotateLeft(newEntry);
					}
					setColor(parentOf(newEntry), BLACK);
                    setColor(parentOf(parentOf(newEntry)), RED);
                    rotateRight(parentOf(parentOf(newEntry)));
				}
			}else{
				 Entry<K,V> p_p_left = leftOf(parentOf(parentOf(newEntry)));
	                if (colorOf(p_p_left) == RED) {
	                    setColor(parentOf(newEntry), BLACK);
	                    setColor(p_p_left, BLACK);
	                    setColor(parentOf(parentOf(newEntry)), RED);
	                    newEntry = parentOf(parentOf(newEntry));
	                } else {
	                    if (newEntry == leftOf(parentOf(newEntry))) {
	                        newEntry = parentOf(newEntry);
	                        rotateRight(newEntry);
	                    }
	                    setColor(parentOf(newEntry), BLACK);
	                    setColor(parentOf(parentOf(newEntry)), RED);
	                    rotateLeft(parentOf(parentOf(newEntry)));
	                }
			}
			
			
			
			
		}
	}
	  /** From CLR */
    private void rotateLeft(Entry<K,V> p) {
        if (p != null) {
            Entry<K,V> r = p.right;
            p.right = r.left;
            if (r.left != null)
                r.left.parent = p;
            r.parent = p.parent;
            if (p.parent == null)
                root = r;
            else if (p.parent.left == p)
                p.parent.left = r;
            else
                p.parent.right = r;
            r.left = p;
            p.parent = r;
        }
    }

    /** From CLR */
    private void rotateRight(Entry<K,V> p) {
        if (p != null) {
            Entry<K,V> l = p.left;
            p.left = l.right;
            if (l.right != null) l.right.parent = p;
            l.parent = p.parent;
            if (p.parent == null)
                root = l;
            else if (p.parent.right == p)
                p.parent.right = l;
            else p.parent.left = l;
            l.right = p;
            p.parent = l;
        }
    }
	private static final boolean RED = false;
	private static final boolean BLACK = true;
	static class Entry<K, V> implements Map.Entry<K, V> {
		
		Entry<K, V> left;
		Entry<K, V> right;
		Entry<K, V> parent;
		boolean color = BLACK;

		/**
		 * Description: Creates a new instance of MyTreeMap.Entry.
		 *
		 */

		public Entry(K key,V value,Entry<K, V> parent ) {
			this.key = key;
			this.value = value;
			this.parent = parent;
		}
		private K key;
		private V value;
		@Override
		public K getKey() {
			return key;
		}

		@Override
		public V getValue() {
			return value;
		}

		@Override
		public V setValue(V value) {
			V oldValue = this.value;
			this.value = value;
			return oldValue;
		}
	}
	public Entry<K, V> leftOf( Entry<K, V> entry) {
		return entry== null?null:entry.left;
	}

	public Entry<K, V> rightOf(Entry<K, V> entry) {
		return entry== null?null:entry.right;
	}

	public Entry<K, V> parentOf(Entry<K, V> entry) {
		return entry== null?null: entry.parent;
	}
	public boolean colorOf(Entry<K, V> entry) {
		return entry== null?BLACK:entry.color;
	}
	public boolean setColor(Entry<K, V> entry, boolean color) {
		if(entry !=null){
			return entry.color=color;
		}else{
			return BLACK;
		}
		
	}
}
