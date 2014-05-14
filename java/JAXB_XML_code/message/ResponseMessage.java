package com.asiainfo.biframe.dam.common.message;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "headerResp", "bodyResp" })
@XmlRootElement(name = "Message")
public class ResponseMessage {

	@XmlElement(name = "HeaderResp")
	private HeaderResp headerResp;

	@XmlElement(name = "BodyResp", required = true)
	private BodyResp bodyResp;

	public HeaderResp getHeaderResp() {
		return headerResp;
	}

	public void setHeaderResp(HeaderResp headerResp) {
		this.headerResp = headerResp;
	}

	public BodyResp getBodyResp() {
		return bodyResp;
	}

	public void setBodyResp(BodyResp bodyResp) {
		this.bodyResp = bodyResp;
	}

}
