package qhy.demo.javassist.myDynamicProxy.test;
/**
 * ClassName: qhy.demo.javassist.myDynamicProxy.test.ObjectModel <br/>
 * Description: TODO 功能描述. <br/>
 * date: 2016年9月8日 下午1:52:59 <br/>
 * Company: gome
 * @author qihuiyong-ds
 * @version 
 */
public class ObjectModel {
	private String name;
	private int age;
	private Long currentTime;
	
	/**
	 * Description: Creates a new instance of ObjectModel.
	 *
	 */

	public ObjectModel(String name,int age,Long currentTime) {
		this.name = name;
		this.age = age;
		this.currentTime = currentTime;
	}
	public ObjectModel() {

		// TODO Auto-generated constructor stub

	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public Long getCurrentTime() {
		return currentTime;
	}
	public void setCurrentTime(Long currentTime) {
		this.currentTime = currentTime;
	}
	
	
}

