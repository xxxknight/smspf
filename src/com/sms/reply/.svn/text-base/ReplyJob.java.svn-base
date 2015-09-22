package com.sms.reply;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.httpclient.NameValuePair;
import org.apache.log4j.Logger;

import com.sms.core.HttpBean;
import com.sms.core.ParentBean;
import com.sms.dao.SmsDao;
import com.sms.pojo.Execution;
import com.sms.pojo.SmsReplyData;

/**
 * <p>
 * Quartz消息回复定期采集入库.
 * </p>
 * 
 * @author 朱文赫
 * @time 2015/1/28
 */
public class ReplyJob extends ParentBean {
	private static Logger logger = Logger.getLogger(ReplyJob.class);
	public SmsDao smsDao;
    /**
     * 短信回复数据定期 采集接口
     */
	public void collectReply() {
		try {
			String url = smsDao.getReplyUrl(); // 回复接口
			String charset = smsDao.getCharset(); // 传输编码
			String successFlag = smsDao.getSuccessFlag(); // 成功标识
			NameValuePair[] nvps = smsDao.getReplyParam(); // 接口参数
			Execution exec = HttpBean.doPost(url, charset, nvps); // HTTP请求执行
			if (exec.getStatus() == 1 && exec.getResult().startsWith(successFlag)) { 
				// TODO 执行消息回复接口成功后业务逻辑
				String resultStr = exec.getResult();
				List<SmsReplyData> lsrd = parseRepStr(resultStr);
				smsDao.saveReply(lsrd);
				logger.info("短信回复接口提取数据[ " + lsrd.size() + " ]条到数据中.");
			} else if (exec.getStatus() == 500) {
				logger.info("短信回复接口HTTP调用异常:" + exec.getException());
			}
		} catch (Exception e) {
			logger.info("短信回复接口任务调用异常:" + e.getMessage());
		}
	}

	private List<SmsReplyData> parseRepStr(String resultStr) throws Exception {
		String keyChar = "replys="; // 消息返回数据标识字符串
		List<SmsReplyData> srdList = new ArrayList<SmsReplyData>();
		if (resultStr.indexOf(keyChar) <= -1) {
			return srdList;
		}
		Timestamp ltsp = smsDao.getLastTime(); // 最后一次提取消息数据的时间点
		String replyStr = resultStr.substring(resultStr.indexOf(keyChar) + keyChar.length(), resultStr.length());
		JSONArray data = JSONArray.fromObject(replyStr);
		Timestamp execTime = new Timestamp(System.currentTimeMillis());
		for (int i = 0; i < data.size(); i++) {
			/* 回复时间了逻辑处理 */
			SmsReplyData srd = new SmsReplyData();
			JSONObject jobj = (JSONObject) data.get(i);
			String replyTime = String.valueOf(jobj.get("reply_time"));
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Timestamp tsp = new Timestamp(format.parse(replyTime).getTime());
			if (ltsp.before(tsp)) { // 数据库中最晚记录时间点以外的数据提取
				srd.setReplyTime(tsp);
				srd.setExecTime(execTime);
				srd.setReplyId(String.valueOf(jobj.get("id")).trim());
				srd.setReplyContent(String.valueOf(jobj.get("content")).trim());
				srd.setReplyMobile(String.valueOf(jobj.get("mdn")).trim());
				srdList.add(srd);
			}
		}
		return srdList;
	}

	/*
	 * private List<SmsReplyData> filterReply(String resultStr) throws Exception
	 * { String keyChar = "replys="; String replyStr =
	 * resultStr.substring(resultStr.indexOf(keyChar) + keyChar.length(),
	 * resultStr.length()); String replyData =
	 * replyStr.substring(replyStr.indexOf("[") + 1, replyStr.lastIndexOf("]"));
	 * Timestamp ltsp = smsDao.getLastTime(); // 最后提取数据的时间 List<SmsReplyData>
	 * srdList = new ArrayList<SmsReplyData>(); for (String str :
	 * replyData.split("},")) { str = str.replaceAll("\\u007B", ""); str =
	 * str.replaceAll(",\\u007B", ""); str = str.replaceAll("}", "");
	 * SmsReplyData srd = new SmsReplyData(); boolean isCk = true; // 入库标识
	 * 根据时间计算最后提取时间 for (String str2 : str.split(",")) { if
	 * (str2.indexOf("\"reply_time\"") > -1) { DateFormat format = new
	 * SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); Timestamp tsp = new
	 * Timestamp(format.parse((str2.substring(13,
	 * str2.length())).replaceAll("\"", "")).getTime()); if
	 * (ltsp.after(tsp)||ltsp.equals(tsp)) { isCk = false; break; }
	 * srd.setReplyTime(tsp); } else if (str2.indexOf("\"content\"") > -1) {
	 * srd.setReplyContent((str2.substring(10, str2.length())).replaceAll("\"",
	 * "")); } else if (str2.indexOf("\"mdn\"") > -1) {
	 * srd.setReplyMobile((str2.substring(6, str2.length())).replaceAll("\"",
	 * "")); } else if (str2.indexOf("\"id\"") > -1) {
	 * srd.setReplyId((str2.substring(5, str2.length())).replaceAll("\"", ""));
	 * }
	 * 
	 * } if (isCk) { srdList.add(srd); } } return srdList; }
	 */

	public SmsDao getSmsDao() {
		return smsDao;
	}

	public void setSmsDao(SmsDao smsDao) {
		this.smsDao = smsDao;
	}
}
