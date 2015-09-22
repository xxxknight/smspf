package com.sms.send;

import javax.jws.WebParam;
import javax.jws.WebService;

@WebService
public interface SendMessage {
	/**
	 * 短信发送接口方法
	 * @param key 本地密码
	 * @param mobile 手机号码  群发用","分隔
	 * @param message 发送消息内容
	 * @return
	 */
	public int sendSms(@WebParam(name="key") String key ,@WebParam(name="mobile")String mobile, @WebParam(name="message")String message);
}
