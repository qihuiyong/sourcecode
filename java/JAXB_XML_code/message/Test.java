package com.asiainfo.biframe.dam.common.message;

import java.io.FileNotFoundException;
import java.io.StringReader;
import java.util.List;

import javax.xml.bind.Binder;
import javax.xml.bind.Element;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.w3c.dom.Node;

import com.asiainfo.biframe.utils.webservice.ResponseContent;

/**
 * xml调用示例
 * @author qihy
 *
 */
public class Test {

	/**
	 * @param args
	 * @throws FileNotFoundException
	 */
	public static void main(String[] args) throws Exception {
		String xml = getFileContent();
		MessageUtil<Chart> mu =new MessageUtil<Chart>(xml);
		List<Chart> list = mu.getResponseContent(Chart.class);
		for (Chart chart : list) {
			System.out.println(chart.getEntranceIds());
		}
	}

	public static String getFileContent() throws Exception {
		String str= "<?xml version=\"1.0\" encoding=\"UTF-8\"?><Message><HeaderResp><RespResult>Success</RespResult><RespTime>20140312213541</RespTime><RespCode>200</RespCode><RespDesc>成功</RespDesc></HeaderResp>"+
		"<BodyResp><RespData>" +
		"<chart type=\"lineageAnalysis\" entranceIds=\"321=22104\"><nodes><node id=\"321=22104\" type=\"Model=321\"><name>TH_B_PAYLOG_DAY</name><flag>1</flag><aliasName/></node>"+
		"<node id=\"228=2577\" type=\"Model=228\"><name>AN5028</name><flag>1</flag><aliasName>AN5028</aliasName></node><node id=\"185=1098\" type=\"Model=185\"><name>th_b_paylog_day.tcl</name><flag>1</flag><aliasName/></node>"+
		"<node id=\"321=24000\" type=\"Model=321\"><name>TF_B_PAYLOG_IN</name><flag>1</flag><aliasName>TF_B_PAYLOG_IN</aliasName></node>"+
		"<node id=\"321=8461\" type=\"Model=321\"><name>TI_AN5028_B_PAYLOG_YYYYMMDD</name><flag>1</flag><aliasName/></node></nodes>"+
		"<edges><edge source=\"321=8461\" target=\"185=1098\"/><edge source=\"228=2577\" target=\"321=8461\"/><edge source=\"185=1098\" target=\"321=22104\"/><edge source=\"321=24000\" target=\"228=2577\"/></edges>"+
		"</chart>" +
		"<chart type=\"lineageAnalysis\" entranceIds=\"321=22104\"><nodes><node id=\"321=22104\" type=\"Model=321\"><name>TH_B_PAYLOG_DAY</name><flag>1</flag><aliasName/></node>"+
		"<node id=\"228=2577\" type=\"Model=228\"><name>AN5028</name><flag>1</flag><aliasName>AN5028</aliasName></node><node id=\"185=1098\" type=\"Model=185\"><name>th_b_paylog_day.tcl</name><flag>1</flag><aliasName/></node>"+
		"<node id=\"321=24000\" type=\"Model=321\"><name>TF_B_PAYLOG_IN</name><flag>1</flag><aliasName>TF_B_PAYLOG_IN</aliasName></node>"+
		"<node id=\"321=8461\" type=\"Model=321\"><name>TI_AN5028_B_PAYLOG_YYYYMMDD</name><flag>1</flag><aliasName/></node></nodes>"+
		"<edges><edge source=\"321=8461\" target=\"185=1098\"/><edge source=\"228=2577\" target=\"321=8461\"/><edge source=\"185=1098\" target=\"321=22104\"/><edge source=\"321=24000\" target=\"228=2577\"/></edges>"+
		"</chart>" +
		"</RespData></BodyResp></Message>";
		return str;
	}

}
