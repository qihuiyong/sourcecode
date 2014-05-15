package com.asiainfo.biframe.dam.common.message;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.Binder;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.w3c.dom.Node;
/**
 * @description 消息解析帮助类
 * @author qihy
 *
 */
public class MessageUtil<T> {
	private ResponseMessage responseMessage;

	/**
	 * 构造函数
	 * @param xmlContent
	 * @throws JAXBException
	 */
	public MessageUtil(String xmlContent) throws JAXBException {
		init(xmlContent);
	}
	/**
	 * 获得responseMessage对象
	 * @return
	 */
	public ResponseMessage getResponseMessage(){
		return responseMessage;
	}
	/**
	 * 获取定义的响应内容
	 * @param clcc
	 * @return
	 * @throws JAXBException
	 */
	public List<T> getResponseContent(Class<T> clcc) throws JAXBException {
		List<Object> listContent = responseMessage.getBodyResp().getRespData()
				.getResponseContent();
		List<T> resultList = null;
		if(listContent!=null && listContent.size()>0){
			resultList= new ArrayList<T>();
			JAXBContext context = JAXBContext.newInstance(clcc);
			Binder<Node> binder = context.createBinder();
			for (Object object : listContent) {
				Object beanObj = binder.unmarshal((Node) object);
				T unmarshal = (T) beanObj;
				resultList.add(unmarshal);
			}
		}
		
		return resultList;
	}
	/**
	 * 初始化
	 * @param xmlContent
	 * @throws JAXBException
	 */
	private void init(String xmlContent) throws JAXBException {
		responseMessage = this.messageBinding(xmlContent);
	}
	/**
	 * 将消息绑定到ResponseMessage
	 * @param xml
	 * @return
	 * @throws JAXBException
	 */
	private ResponseMessage messageBinding(String xml) throws JAXBException {
		JAXBContext context = JAXBContext.newInstance(ResponseMessage.class);
		Unmarshaller um = context.createUnmarshaller();
		return (ResponseMessage) um.unmarshal(new StringReader(xml));
	}
}
