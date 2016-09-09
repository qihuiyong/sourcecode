package qhy.demo.javassist;
/**
 * ClassName: qhy.demo.javassist.MyClass <br/>
 * Description: TODO 功能描述. <br/>
 * date: 2016年8月31日 下午5:34:29 <br/>
 * Company: gome
 * @author qihuiyong-ds
 * @version 
 */
public class Echo{
	public String echo(){
		System.out.println("【"+name+"】今年已经【"+age+"】岁");
		return name+"\t-->\t"+age;
	}
	
	private String name;
	private Integer age;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	
}

