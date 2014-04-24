package org.qhy.test;

import java.io.IOException;
import java.net.URLEncoder;

import org.apache.commons.httpclient.Cookie;
import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.UsernamePasswordCredentials;
import org.apache.commons.httpclient.auth.AuthScope;
import org.apache.commons.httpclient.cookie.CookiePolicy;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;

public class TestInvokeBaiduLogin {

	public static void main(String[] args) throws HttpException, IOException {
		// 创建httpClient客户端
		HttpClient client = new HttpClient();
		// 百度的登陆url
		String loginUrl = "https://passport.baidu.com/v2/api/?login";
		// 设置代理
		String httpProxyHost = "10.1.251.240";
		int proxyPort = 808;
		client.getHostConfiguration().setProxy(httpProxyHost, proxyPort);
		// 代理验证信息
		client.getParams().setAuthenticationPreemptive(true);
		UsernamePasswordCredentials creds = new UsernamePasswordCredentials(
				"pdd", "pdd");
		client.getState().setProxyCredentials(AuthScope.ANY, creds);
		// 代理设置结束(如果不需要代理这段可以注释掉)
		// 登陆百度
		PostMethod psotMethod = new PostMethod(loginUrl);
		// 设置登陆参数
		NameValuePair[] data = {
				new NameValuePair("class", "login"),
				new NameValuePair("tpl", "mn"),
				new NameValuePair("tangram", "true"),
				new NameValuePair("isPhone", "false"),
				new NameValuePair("loginType", "1"),
				new NameValuePair("token", "f6994339731518f4c50d30acc10cbc6f"),
				new NameValuePair("callback",
						"parent.bdPass.api.login._postCallback"),
				new NameValuePair("username", "xxxxx"),
				new NameValuePair("password", "xxx") };
		psotMethod.setRequestBody(data);
		// HttpClient像浏览器一样接收 Cookie,
		client.getParams().setCookiePolicy(CookiePolicy.BROWSER_COMPATIBILITY);
		client.executeMethod(psotMethod);
		Header[] haeders = psotMethod.getRequestHeaders();
		// 获取cookie
		Cookie[] cookies = client.getState().getCookies();
		String cookiesStr = "";
		for (Cookie c : cookies) {
			cookiesStr += c.toString() + ";";
		}
		System.out.println("psotMethod--response:"
				+ new String(psotMethod.getResponseBody(), "UTF-8"));
		psotMethod.releaseConnection();
		// 使用 GET 方法 ，如果服务器需要通过 HTTPS 连接，那只需要将下面 URL 中的 http 换成 https
		HttpMethod method = new GetMethod(
				"http://api.map.baidu.com/place/v2/search?&q="
						+ URLEncoder.encode("饭店", "UTF-8") + "&region="
						+ URLEncoder.encode("北京", "UTF-8")
						+ "&output=json&ak=1649c026d0cea10ccf5741348526cb44");
		method.setRequestHeader("Cookie", cookiesStr);
		method.getParams().setBooleanParameter(
				HttpMethodParams.SINGLE_COOKIE_HEADER, true);
		client.executeMethod(method);
		// 打印 服务器状态
		System.out.println("状态：" + method.getStatusLine());
		// 打印信息
		System.out.println("内容:"
				+ new String(method.getResponseBody(), "UTF-8"));
		// 关闭连接
		method.releaseConnection();
	}
}
