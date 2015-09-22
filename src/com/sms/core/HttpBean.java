package com.sms.core;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;

import com.sms.pojo.Execution;
/**
 * <p>HTTPЭ��ͻ���.</p>
 * @author ���ĺ�
 * @time 2015/1/26
 *
 */
public class HttpBean {
	/**
	 * POST����ʽ�ύ���󵽷������˷���
	 * @param url �ӿ�URL��ַ
	 * @param param �ύ�Ĳ���
	 * @return 
	 * 	0/��ʱ���ܾ���������δ��Ӧ
	 * 	1/ִ�з��ͳɹ� 
	 * 	500/ִ���쳣
	 */
	public static Execution doPost(String url,String charset,NameValuePair[] param) {
		// ����HttpClient��ʵ�� 
		HttpClient httpClient = new HttpClient();
		PostMethod httpPost = new PostMethod(url);
		httpPost.setRequestBody(param);
		Execution ec = new Execution();
		// ʹ��ϵͳ�ṩ��Ĭ�ϵĻָ�����
		httpClient.getParams().setParameter("http.protocol.content-charset", charset);
		try {
			int statusCode = httpClient.executeMethod(httpPost);
			if (statusCode != HttpStatus.SC_OK) {
				ec.setStatus(0);
				ec.setException("��ʱ�����˾ܾ�����.");
				return ec;
			}
			byte[] responseBody = httpPost.getResponseBody();
			ec.setStatus(1);
			ec.setResult(new String(responseBody));
			return  ec;
		} catch (Exception e) {
			ec.setStatus(500);
			ec.setException("ִ��ʧ�ܣ�"+e.getMessage());
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
		param[4] = new NameValuePair("MessageContent", "������֤��Ϊ123456");
		System.out.println(HttpBean.doPost("http://hebei.ums86.com:8899/sms/Api/Send.do", "GB2312", param));
	}
}
