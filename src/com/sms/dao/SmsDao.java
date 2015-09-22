package com.sms.dao;

import java.sql.Timestamp;
import java.util.List;

import org.apache.commons.httpclient.NameValuePair;

import com.sms.pojo.SmsCallHistory;
import com.sms.pojo.SmsReplyData;
/**
 * <p>�������ݷ��ʽӿ�.</p>
 * @author ���ĺ�
 * @time 2015/1/28
 *
 */
public interface SmsDao {
	/**
	 * ȡ���Ͷ��Žӿ�URL��ַ
	 * @return
	 */
	public String getSendUrl();
	/**
	 * ȡ�ظ����Žӿ�URL��ַ
	 * @return
	 */
	public String getReplyUrl();
	/**
	 * ȡ�ӿ��ַ������ʽ
	 * @return
	 */
	public String getCharset();
	/**
	 * ����������֤
	 * @param keyCode ����
	 * @return
	 */
	public boolean checkKeyCode(String keyCode);
	/**
	 * ȡ���Ͷ��ŽӿڵĲ�������
	 * @param mobile �ֻ���
	 * @param message ����
	 * @return
	 */
	public NameValuePair[] getSendParam(String mobile,String message);
	/**
	 * ȡ�ظ����ŽӿڵĲ�������
	 * @return
	 */
	public NameValuePair[] getReplyParam();
	/**
	 * ȡ���ŷ��ͳɹ���ʶ�ַ���
	 * @return
	 */
	public String getSuccessFlag ();
	
	/**
	 * ����ӿ���־
	 * @param sch
	 * @return
	 */
	public int saveLog(SmsCallHistory sch);
	
	/**
	 * ȡ���ɼ����Żظ���ʱ��
	 * @return
	 */
	public Timestamp getLastTime()throws Exception;
	/**
	 * ������Żظ��ɼ���������
	 * @param srd
	 * @return
	 */
	public int saveReply(List<SmsReplyData> lsrd);
}
