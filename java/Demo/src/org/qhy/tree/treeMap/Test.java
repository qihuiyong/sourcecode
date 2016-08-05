package org.qhy.tree.treeMap;

import java.util.List;

import org.qhy.tree.treeMap.MyTreeMap.Entry;


/**
 * ClassName: org.qhy.tree.treeMap.Test <br/>
 * Description: TODO 功能描述. <br/>
 * date: 2016年8月1日 上午9:43:11 <br/>
 * Company: gome
 * @author qihuiyong-ds
 * @version 
 */
public class Test {

	/**
	 * Description: . <br/>
	 * @param args
	 */
	public static void main(String[] args) {
		MyTreeMap<Integer, Integer> treeMap = new MyTreeMap<Integer, Integer>();
		for(int i=0;i<1000;i++){
			double random = Math.random();
			int val = (int)(random*10000);
			treeMap.put(val, val);
		}
		
		List<Entry<Integer, Integer>> list = treeMap.getAll();
		System.out.println("count>>>>>>>>>>>>>>"+list.size());
		print(treeMap.getRoot());
	}
	public static void print(Entry<Integer, Integer> node){
		Entry<Integer, Integer> tmp = node;
		System.out.println(tmp.getValue());
			if(tmp.left!=null){
				System.out.println(tmp.left.getValue());
			}
			
			if(tmp.right!=null){
				System.out.println(tmp.right.getValue());
			}
			
			if(tmp.left!=null){
				print(tmp.left);
			}
			if(tmp.right!=null){
				print(tmp.right);
			}
		
	}

	/**
	 * 
	 * Description: . <br/>
	 * @param node
	 * @param type 1找最小的，2找最大的
	 */
	public static Entry<Integer, Integer> getLeaf(Entry<Integer, Integer> node,int type){
		if(type == 1){
			if(node.left!=null)
			return getLeaf(node.left,type);
		}else{
			if(node.right!=null)
			return getLeaf(node.right,type);
		}
		
		return node;
	}

}

