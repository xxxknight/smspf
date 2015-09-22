package com.sms.send.impl;


import java.sql.Timestamp;

import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.httpclient.NameValuePair;
import org.apache.cxf.message.Message;
import org.apache.cxf.phase.PhaseInterceptorChain;
import org.apache.cxf.transport.http.AbstractHTTPDestination;
import org.apache.log4j.Logger;

import com.sms.core.HttpBean;
import com.sms.core.ParentBean;
import com.sms.dao.SmsDao;
import com.sms.pojo.Execution;
import com.sms.pojo.SmsCallHistory;
import com.sms.reply.ReplyJob;
import com.sms.send.SendMessage;
/**
 * WebService ���ŷ��ͽӿ�ʵ��.
 * @author zhuwh
 */
@WebService
@SOAPBinding(style = Style.RPC)
@SuppressWarnings("unchecked")
public class SendMessageImpl extends ParentBean implements SendMessage {
	private static Logger logger = Logger.getLogger(SendMessageImpl.class);
	public SmsDao smsDao;

	public int sendSms(String key, String mobile, String message) {
		try {
			if (null != key && !key.trim().equals("") && smsDao.checkKeyCode(key)) {
				int result = 0;
				String url = smsDao.getSendUrl(); // ���Ͷ��Žӿ�
				String charset = smsDao.getCharset(); // �ַ�����
				String successFlag = smsDao.getSuccessFlag(); // �ɹ���ʾ��
				NameValuePair[] nvp = smsDao.getSendParam(mobile, message); // �ӿڱ�Ҫ����
				SmsCallHistory sch = new SmsCallHistory(); // ��¼��־ 
				sch.setReqIpAddr("");
				sch.setSendMsg(message);
				sch.setMobile(mobile);
				sch.setCallTime(new Timestamp(System.currentTimeMillis()));
				sch.setInfDesc("���ŷ���");
				sch.setReqIpAddr(getClientIp());
				sch.setInfUrl(url);
				sch.setLocalParam(getParamStr(nvp));
				Execution exec = HttpBean.doPost(url, charset, nvp); // HTTP�������Ŀ��
				sch.setResultMsg(exec.result == null ? "" : exec.result);
				if (exec.getStatus() == 1 && exec.getResult().startsWith(successFlag)) { // ���Ͷ��ųɹ�
					result = 1;
				} else if (exec.getStatus() == 500) {
					result = 500;
				}
				sch.setStatus(String.valueOf(result));
				smsDao.saveLog(sch);
				logger.info("���ö��ŷ��ͽӿ�[result="+result+",mobile="+mobile+",message="+message+"]");
				return result;
			} else {
				System.out.println("hah");
				return 2;
			}
		} catch (Exception e) {
			logger.info("���ŷ��ͽӿڵ����쳣(500):"+e.getMessage());
			return 500;
		}
		// �ɹ���ʾresult=0&description=���Ͷ��ųɹ�&taskid=21084957330&faillist=&task_id=21084957330
	}

	public SmsDao getSmsDao() {
		return smsDao;
	}

	public void setSmsDao(SmsDao smsDao) {
		this.smsDao = smsDao;
	}

}