package com.sms.client;

import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;

public class client {
	public static void main(String[] args) {
		long yzCode= Math.round(Math.random()*899999+100000); 
		System.out.println(yzCode );
		System.err.println(sendMessage("13717951148","您注册的唐山市网上行政审批大厅账号验证码为"+yzCode+""));
	}
	
	public static int sendMessage(String mobile,String message){
		try {
			JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
			org.apache.cxf.endpoint.Client client = dcf.createClient("http://101.200.173.2:7001/smspf/sendMessage?wsdl");
			// 调用远程方法返回数组 参数(1.远程方法名,2.参数,3.参数,4.参数)
			Object[] objects = client.invoke("sendSms","123456", mobile, message);
			System.out.println(11);
			return Integer.valueOf(String.valueOf(objects[0]));
		} catch (Exception e) {
			return 500;
		}
	}
}
