package org.qhy.util;

import java.util.Date;
/**
 * 
 * ClassName: org.qhy.util.StringUtil <br/>
 * Description: 啥地方都是非法的规范梵蒂冈的方法的. <br/>
 * date: 2016年6月3日 下午5:49:39 <br/>
 * Company: gome
 * @author qihuiyong-ds
 * @version
 */
public class StringUtil {
	/**
	 * Description helloWorld. <br/>
	 * @return
	 */
	public static String getHelloworld() {
		return "hello world!";
	}
	/**
	 * 
	 * Description 你说的啥地方大幅度说的 . <br/>
	 * @return
	 */
	public Date getTime() {
		return new Date();
	}

	/**
	 * Description: 打印控制台信息 . <br/>
	 * @param name 人员姓名
	 */
	public void sayHi(String name) {
		System.out.println("hi ':" + name);
	}
}
