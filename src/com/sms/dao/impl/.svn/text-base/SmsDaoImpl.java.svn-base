package com.sms.dao.impl;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import org.apache.commons.httpclient.NameValuePair;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;

import com.sms.dao.SmsDao;
import com.sms.pojo.SmsCallHistory;
import com.sms.pojo.SmsReplyData;

public class SmsDaoImpl implements SmsDao {
	private JdbcTemplate jdbcTemplate;

	public String getCharset() {
		List<Map<String, Object>> result = jdbcTemplate.queryForList("select param_value from sms_param where param_code = 'bmgs'");
		return result.size() > 0 ? String.valueOf(result.get(0).get("param_value")) : "";
	}

	public String getSendUrl() {
		List<Map<String, Object>> result = jdbcTemplate.queryForList("select inf_url from sms_interface where inf_code = 'send'");
		return result.size() > 0 ? String.valueOf(result.get(0).get("inf_url")) : "";
	}

	public String getReplyUrl() {
		List<Map<String, Object>> result = jdbcTemplate.queryForList("select inf_url from sms_interface where inf_code = 'reply'");
		return result.size() > 0 ? String.valueOf(result.get(0).get("inf_url")) : "";
	}

	public String getSuccessFlag() {
		List<Map<String, Object>> result = jdbcTemplate.queryForList("select param_value from sms_param where param_code = 'cgbs'");
		return result.size() > 0 ? String.valueOf(result.get(0).get("param_value")) : "";
	}

	public boolean checkKeyCode(String keyCode) {
		if (null != keyCode && !keyCode.trim().equals("")) {
			List<Map<String, Object>> result = jdbcTemplate.queryForList("select param_value from sms_param where param_code = 'bdmm'");
			if (result.size() > 0) {
				return true;
			}
		}
		return false;
	}

	public NameValuePair[] getSendParam(String mobile, String message) {
		List<Map<String, Object>> result = jdbcTemplate.queryForList("select p.* from sms_interface_mp_param m , sms_param p where m.param_code = p.param_code and m.inf_code = 'send'");
		NameValuePair[] nameValuePair = new NameValuePair[result.size()];
		for (int i = 0; i < result.size(); i++) {
			Map map = result.get(i);
			String paramValue = map.get("param_value").toString();
			if (map.get("param_code").toString().equals("sjhm")) {
				paramValue = mobile;
			} else if (map.get("param_code").toString().equals("fsnr")) {
				paramValue = message;
			}
			nameValuePair[i] = new NameValuePair(map.get("param_key").toString(), paramValue);
		}
		return nameValuePair;
	}

	public NameValuePair[] getReplyParam() {
		List<Map<String, Object>> result = jdbcTemplate.queryForList("select p.* from sms_interface_mp_param m , sms_param p where m.param_code = p.param_code and m.inf_code = 'reply'");
		NameValuePair[] nameValuePair = new NameValuePair[result.size()];
		for (int i = 0; i < result.size(); i++) {
			Map map = result.get(i);
			String paramValue = map.get("param_value").toString();
			nameValuePair[i] = new NameValuePair(map.get("param_key").toString(), paramValue);
		}
		return nameValuePair;
	}

	public int saveLog(SmsCallHistory sch) {
		Object[] array = new Object[9];
		array[0] = sch.getReqIpAddr();
		array[1] = sch.getInfDesc();
		array[2] = sch.getMobile();
		array[3] = sch.getInfUrl();
		array[4] = sch.getSendMsg();
		array[5] = sch.getResultMsg();
		array[6] = sch.getCallTime();
		array[7] = sch.getStatus();
		array[8] = sch.getLocalParam();
		jdbcTemplate.update("insert into  sms_call_history (req_ip_addr,inf_desc,mobile,inf_url,send_msg,result_msg,call_time,status,local_param) VALUES (?,?,?,?,?,?,?,?,?)", array);
		return 0;
	}

	public Timestamp getLastTime() throws Exception {
		List<Map<String, Object>> result = jdbcTemplate.queryForList("select max(reply_time)'last' from sms_reply_data");
		if (!result.isEmpty()&&result.get(0).get("last")!=null) {
			String lastStr = String.valueOf(result.get(0).get("last"));
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Timestamp tsp = new Timestamp(format.parse(lastStr).getTime());
			return tsp;
		}
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return  new Timestamp(format.parse("2015-1-1 00:00:00").getTime());
	}
	
	public int saveReply(final List<SmsReplyData> lsrd) {
		jdbcTemplate.batchUpdate("insert into sms_reply_data (reply_id,reply_content,reply_time,reply_mobile,exec_time)values(?,?,?,?,?)", new BatchPreparedStatementSetter() {
			public void setValues(PreparedStatement arg0, int arg1) throws SQLException {
				arg0.setString(1, lsrd.get(arg1).getReplyId());
				arg0.setString(2, lsrd.get(arg1).getReplyContent());
				arg0.setTimestamp(3, lsrd.get(arg1).getReplyTime());
				arg0.setString(4, lsrd.get(arg1).getReplyMobile());
				arg0.setTimestamp(5, lsrd.get(arg1).getExecTime());
			}
			public int getBatchSize() {
				return lsrd.size();
			}
		});
		return 0;
	}

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	

}
