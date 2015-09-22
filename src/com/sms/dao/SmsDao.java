package com.sms.dao;

import java.sql.Timestamp;
import java.util.List;

import org.apache.commons.httpclient.NameValuePair;

import com.sms.pojo.SmsCallHistory;
import com.sms.pojo.SmsReplyData;
/**
 * <p>常用数据访问接口.</p>
 * @author 朱文赫
 * @time 2015/1/28
 *
 */
public interface SmsDao {
	/**
	 * 取发送短信接口URL地址
	 * @return
	 */
	public String getSendUrl();
	/**
	 * 取回复短信接口URL地址
	 * @return
	 */
	public String getReplyUrl();
	/**
	 * 取接口字符编码格式
	 * @return
	 */
	public String getCharset();
	/**
	 * 本地密码验证
	 * @param keyCode 密码
	 * @return
	 */
	public boolean checkKeyCode(String keyCode);
	/**
	 * 取发送短信接口的参数集合
	 * @param mobile 手机号
	 * @param message 内容
	 * @return
	 */
	public NameValuePair[] getSendParam(String mobile,String message);
	/**
	 * 取回复短信接口的参数集合
	 * @return
	 */
	public NameValuePair[] getReplyParam();
	/**
	 * 取短信发送成功标识字符串
	 * @return
	 */
	public String getSuccessFlag ();
	
	/**
	 * 保存接口日志
	 * @param sch
	 * @return
	 */
	public int saveLog(SmsCallHistory sch);
	
	/**
	 * 取最后采集短信回复的时间
	 * @return
	 */
	public Timestamp getLastTime()throws Exception;
	/**
	 * 保存短信回复采集到的数据
	 * @param srd
	 * @return
	 */
	public int saveReply(List<SmsReplyData> lsrd);
}
