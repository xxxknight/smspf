package com.sms.core;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;

import com.sms.pojo.Execution;
/**
 * <p>HTTP协议客户端.</p>
 * @author 朱文赫
 * @time 2015/1/26
 *
 */
public class HttpBean {
	/**
	 * POST的形式提交请求到服务器端方法
	 * @param url 接口URL地址
	 * @param param 提交的参数
	 * @return 
	 * 	0/超时、拒绝、服务器未响应
	 * 	1/执行发送成功 
	 * 	500/执行异常
	 */
	public static Execution doPost(String url,String charset,NameValuePair[] param) {
		// 构造HttpClient的实例 
		HttpClient httpClient = new HttpClient();
		PostMethod httpPost = new PostMethod(url);
		httpPost.setRequestBody(param);
		Execution ec = new Execution();
		// 使用系统提供的默认的恢复策略
		httpClient.getParams().setParameter("http.protocol.content-charset", charset);
		try {
			int statusCode = httpClient.executeMethod(httpPost);
			if (statusCode != HttpStatus.SC_OK) {
				ec.setStatus(0);
				ec.setException("超时或服务端拒绝访问.");
				return ec;
			}
			byte[] responseBody = httpPost.getResponseBody();
			ec.setStatus(1);
			ec.setResult(new String(responseBody));
			return  ec;
		} catch (Exception e) {
			ec.setStatus(500);
			ec.setException("执行失败："+e.getMessage());
			return ec;
		} finally {
			httpPost.releaseConnection();
		}
	}
	
	public static void main(String[] args){
		NameValuePair[] param = new NameValuePair[5];
		param[0] = new NameValuePair("SpCode", "201446");
		param[1] = new NameValuePair("LoginName", "hb_hhyk");
		param[2] = new NameValuePair("Password", "hhyk1234");
		param[3] = new NameValuePair("UserNumber", "18811726825");
		param[4] = new NameValuePair("MessageContent", "您的验证码为123456");
		System.out.println(HttpBean.doPost("http://hebei.ums86.com:8899/sms/Api/Send.do", "GB2312", param));
	}
}
