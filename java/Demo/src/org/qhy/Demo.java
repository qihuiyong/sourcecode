package org.qhy;

import io.netty.handler.codec.DelimiterBasedFrameDecoder;

/**
 * ClassName: org.qhy.Demo <br/>
 * Description: TODO 功能描述. <br/>
 * date: 2016年8月18日 上午9:56:52 <br/>
 * Company: gome
 * @author qihuiyong-ds
 * @version 
 */
public class Demo {
	private boolean isSubclass() {
        return getClass() != Demo.class;
    }
	public void aa(){
		System.out.println(isSubclass());
	}
	/**
	 * Description: . <br/>
	 * @param args
	 */
	public static void main(String[] args) {

		Demo d = new DemoChild();
		d.aa();
	}

}

