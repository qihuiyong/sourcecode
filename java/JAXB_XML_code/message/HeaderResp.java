package com.asiainfo.biframe.dam.common.message;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * @description HeaderResp标签
 * @author qihy
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "respResult", "respTime", "respCode",
		"respDesc" })
@XmlRootElement(name = "HeaderResp")
public class HeaderResp {

	@XmlElement(name = "RespResult")
	private String respResult;

	@XmlElement(name = "RespTime")
	private String respTime;

	@XmlElement(name = "RespCode")
	private String respCode;

	@XmlElement(name = "RespDesc")
	private String respDesc;
//
//	@XmlElement(name = "TokenCode")
//	private String tokenCode;

	public String getRespResult() {
		return respResult;
	}

	public void setRespResult(String respResult) {
		this.respResult = respResult;
	}

	public String getRespTime() {
		return respTime;
	}

	public void setRespTime(String respTime) {
		this.respTime = respTime;
	}

	public String getRespCode() {
		return respCode;
	}

	public void setRespCode(String respCode) {
		this.respCode = respCode;
	}

	public String getRespDesc() {
		return respDesc;
	}

	public void setRespDesc(String respDesc) {
		this.respDesc = respDesc;
	}
//
//	public String getTokenCode() {
//		return tokenCode;
//	}
//
//	public void setTokenCode(String tokenCode) {
//		this.tokenCode = tokenCode;
//	}

}
