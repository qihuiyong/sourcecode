package org.qhy.test;

import java.io.IOException;
import java.net.URLEncoder;

import org.apache.commons.httpclient.Credentials;
import org.apache.commons.httpclient.HostConfiguration;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpHost;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.UsernamePasswordCredentials;
import org.apache.commons.httpclient.auth.AuthScope;
import org.apache.commons.httpclient.methods.GetMethod;

public class TestProxy {

	public static void main(String[] args) throws HttpException, IOException {
		HttpClient client = new HttpClient();
		
		// 设置代理服务器地址和端口
		client.getHostConfiguration().setProxy("10.1.251.240", 808);
		 client.getParams().setAuthenticationPreemptive(true);  
		 UsernamePasswordCredentials creds = new UsernamePasswordCredentials("pdd", "pdd");
		 client.getState().setProxyCredentials(AuthScope.ANY, creds);
		// 使用 GET 方法 ，如果服务器需要通过 HTTPS 连接，那只需要将下面 URL 中的 http 换成 https
		HttpMethod method = new GetMethod("http://api.map.baidu.com/place/v2/search?&q="
				+ URLEncoder.encode("饭店", "UTF-8") + "&region="
				+ URLEncoder.encode("北京", "UTF-8")
				+ "&output=json&ak=1649c026d0cea10ccf5741348526cb44");
		// 使用POST方法
		// HttpMethod method = new PostMethod("http://java.sun.com");
		client.executeMethod(method);

		// 打印服务器返回的状态
		System.out.println("状态："+method.getStatusLine());
		// 打印返回的信息
		System.out.println("返回内容:"+new String(method.getResponseBody(),"UTF-8"));
		// 释放连接
		method.releaseConnection();

	}
}
