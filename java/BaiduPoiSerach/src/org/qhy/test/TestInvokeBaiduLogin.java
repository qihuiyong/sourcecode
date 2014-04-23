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

public class TestInvokeBaiduLogin {

	public static void main(String[] args) throws HttpException, IOException {
		HttpClient client = new HttpClient();
		String loginUrl = "https://passport.baidu.com/v2/api/?login";
		String tokenUrl = "https://passport.baidu.com/v2/api/?getapi&class=login&tpl=mn&tangram=true";
		// String uNameDomName = "userName", uPasswordDomName = "password";
		// 设置代理服务器地址和端口
		client.getHostConfiguration().setProxy("10.1.251.240", 808);
		client.getParams().setAuthenticationPreemptive(true);
		UsernamePasswordCredentials creds = new UsernamePasswordCredentials(
				"pdd", "pdd");
		client.getState().setProxyCredentials(AuthScope.ANY, creds);
		// 登陆百度
		PostMethod psotMethod = new PostMethod(loginUrl);
//		// 获取token
//		HttpMethod tokenGet = new GetMethod(tokenUrl);
//		client.executeMethod(tokenGet);
//		BufferedReader br = new BufferedReader(new InputStreamReader(
//				tokenGet.getResponseBodyAsStream(), "UTF-8"));
//		String line = "";
//		while ((line = br.readLine()) != null) {
//			if (line.contains("login_token")) {
//				line = line.substring(line.indexOf("='") + 2,
//						line.indexOf("';"));
//				break;
//			}
//		}

		// params2.put("token", r.getToken());

		// 设置登陆时要求的信息，一般就用户名和密码，验证码自己处理了
		NameValuePair[] data = {
				new NameValuePair("class", "login"),
				new NameValuePair("tpl", "mn"),
				new NameValuePair("tangram", "true"),
				new NameValuePair("isPhone", "false"),
				new NameValuePair("loginType", "1"),
				new NameValuePair("verifycode", ""),
				new NameValuePair("token", "f6994339731518f4c50d30acc10cbc6f"),
				new NameValuePair("callback",
						"parent.bdPass.api.login._postCallback"),
				new NameValuePair("username", "xxxxx"),
				new NameValuePair("password", "xxx")};
		psotMethod.setRequestBody(data);
		// 设置 HttpClient 接收 Cookie,用与浏览器一样的策略
		client.getParams().setCookiePolicy(CookiePolicy.BROWSER_COMPATIBILITY);
		client.executeMethod(psotMethod);
		Header[] haeders = psotMethod.getRequestHeaders();
		// 获得登陆后的 Cookie
		Cookie[] cookies = client.getState().getCookies();
		String tmpcookies = "";
		for (Cookie c : cookies) {
			tmpcookies += c.toString() + ";";
		}
		System.out.println("psotMethod--response:"
				+ new String(psotMethod.getResponseBody(), "UTF-8"));

		// 使用 GET 方法 ，如果服务器需要通过 HTTPS 连接，那只需要将下面 URL 中的 http 换成 https
		HttpMethod method = new GetMethod(
				"http://api.map.baidu.com/place/v2/search?&q="
						+ URLEncoder.encode("饭店", "UTF-8") + "&region="
						+ URLEncoder.encode("北京", "UTF-8")
						+ "&output=json&ak=1649c026d0cea10ccf5741348526cb44");
//		 method.setRequestHeader("cookie", tmpcookies);
//		 method.getParams().setBooleanParameter(HttpMethodParams.SINGLE_COOKIE_HEADER, true);
		// for (Header header : haeders) {
		// method.setRequestHeader(header);
		// }
		// BAIDUID=49ED0908707E55D43F5DD12FDFA5A2E3:FG=1;
		// 使用POST方法
		// HttpMethod method = new PostMethod("http://java.sun.com");
		client.executeMethod(method);
		// 打印服务器返回的状态
		System.out.println("状态：" + method.getStatusLine());
		// 打印返回的信息
		System.out.println("返回内容:"
				+ new String(method.getResponseBody(), "UTF-8"));
		// 释放连接
		method.releaseConnection();
	}
}
