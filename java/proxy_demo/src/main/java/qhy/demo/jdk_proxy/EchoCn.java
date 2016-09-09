package qhy.demo.jdk_proxy;
/**
 * ClassName: org.qhy.proxy.EchoImpl1 <br/>
 * Description: TODO 功能描述. <br/>
 * date: 2016年8月31日 上午11:56:21 <br/>
 * Company: gome
 * @author qihuiyong-ds
 * @version 
 */
public class EchoCn implements IEcho {

	/**
	 * Description: TODO 简单描述该方法的实现功能（可选）.
	 * @see qhy.demo.jdk_proxy.IEcho#echo(java.lang.String)
	 */
	@Override
	public String echo(String name) {
		System.out.println("你好 "+name +"!");
		return "完毕!";
	}

}

