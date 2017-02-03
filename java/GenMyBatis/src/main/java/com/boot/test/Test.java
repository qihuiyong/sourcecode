package com.boot.test;

public class Test {

	public static void main(String[] args) {
		int month =10; //要查询的月份
		for (int z=1;z<=24;z++) {
			month = z;
		
		 int[] m = new int[month];
		    int i;  
		    if(month == 1){
		    	 m[0]=1;  
		    }else if(month >= 2){
		    	m[0]=m[1]=1; 
		    }
		     
		    for (i=0; i<m.length; i++) {  
		        if (i<2) {  
		        	continue;
		        }else {  
		           if(i > 4){ //开始死兔子
		        	   m[i]=m[i-1]+m[i-2];
		        	   int sw = m[i-5];
		        	   for (int j = 0; j <= i; j++) {
		        		   m[j] -= sw;
		        	   }
		   		   }else{
		   			m[i]=m[i-1]+m[i-2];  
		   		   }
		            
		        }  
		    }
		    
		    System.out.println("死之后>>>>>>>第"+month+"个月月兔子数量为:"+m[month-1]+"对");
		}
		   
	}
	public static void main111(String[] args) {
		int month =4;
		
		 int[] m = new int[24];
		    int i;  
		    m[0]=m[1]=1;  
		    for (i=0; i<24; i++) {  
		        if (i==0 || i==1) {  
		        	System.out.println("第"+(i+1)+"个月月兔子数量为:1对");
		        	continue;
		        }  
		        else {  
		            m[i]=m[i-1]+m[i-2];  
		            System.out.println("第"+(i+1)+"个月月兔子数量为:"+m[i]+"对");
		        }  
		    }
		    

	}
	
}
