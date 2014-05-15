package com.asiainfo.biframe.dam.common.message;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @description RespData标签
 * @author qihy
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "RespData")
public class RespData {
	
	@XmlAnyElement
	private List<Object> responseContent;
	
	public List<Object> getResponseContent() {
		return responseContent;
	}
	public void setResponseContent(List<Object> responseContent) {
		this.responseContent = responseContent;
	}
	
	
	
//	@XmlElement(name = "chart",type=Chart.class)
//	private Chart chart;
//
//	public Chart getChart() {
//		return chart;
//	}
//
//	public void setChart(Chart chart) {
//		this.chart = chart;
//	}

}
