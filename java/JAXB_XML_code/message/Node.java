package com.asiainfo.biframe.dam.common.message;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @description node标签
 * @author qihy
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "node")
public class Node {
	@XmlAttribute(name = "id")
	private String id;
	@XmlAttribute(name = "type")
	private String type;
	@XmlElement(name = "name")
	private String name;
	@XmlElement(name = "flag")
	private String flag;
	@XmlElement(name = "aliasName")
	private String aliasName;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getAliasName() {
		return aliasName;
	}

	public void setAliasName(String aliasName) {
		this.aliasName = aliasName;
	}

}