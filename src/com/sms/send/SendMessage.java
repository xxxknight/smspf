package com.sms.send;

import javax.jws.WebParam;
import javax.jws.WebService;

@WebService
public interface SendMessage {
	/**
	 * ���ŷ��ͽӿڷ���
	 * @param key ��������
	 * @param mobile �ֻ�����  Ⱥ����","�ָ�
	 * @param message ������Ϣ����
	 * @return
	 */
	public int sendSms(@WebParam(name="key") String key ,@WebParam(name="mobile")String mobile, @WebParam(name="message")String message);
}
