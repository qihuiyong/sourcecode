package com.asiainfo.biframe.dam.common.message;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @author qihy
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "BodyResp")
public class BodyResp {

	@XmlElement(name = "RespData", required = true)
	private RespData respData;

	public RespData getRespData() {
		return respData;
	}

	public void setRespData(RespData respData) {
		this.respData = respData;
	}

}
