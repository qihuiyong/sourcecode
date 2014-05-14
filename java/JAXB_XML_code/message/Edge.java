package com.asiainfo.biframe.dam.common.message;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 与SS系统调度接口使用的实体类Edge节点
 * 
 * @author qihy
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "edge")
public class Edge {
	@XmlAttribute(name = "source")
	private String source;
	@XmlAttribute(name = "target")
	private String target;

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

}