package com.sms.core;

/**
 * <p>��̬���ݿ��л�ȡ������.<p>
 * @author ���ĺ�
 * @time 2015/1/27
 *
 */
@SuppressWarnings("unchecked")
public class ParamDao {
	/**
	 * ȡ���Ͷ��Žӿ�URL
	 * @return
	 */
/*	public String getSendUrl(){
		JdbcConn jdbcConn = new JdbcConn();
		List<Map> result = jdbcConn.execQuery("select inf_url from sms_interface where inf_code = 'send'"); // send Ϊ����keyֵ���ݿ�key�����޸�
		if(result.size() > 0){
			return result.get(0).get("inf_url").toString();
		}
		return "";
	}
	*//**
	 * ȡ�ӿڴ����ַ��������ʽ
	 * @return
	 *//*
	public String getCharset(){
		JdbcConn jdbcConn = new JdbcConn();
		List<Map> result = jdbcConn.execQuery("select param_value from sms_param where param_code = 'bmgs'"); 
		if(result.size() > 0){
			return result.get(0).get("param_value").toString();
		}
		return "";
	}
	*//**
	 * ���Žӿ��ύ�Ĳ���
	 * @return
	 *//*
	public NameValuePair[] getSendParam(String mobile,String message){
		JdbcConn jdbcConn = new JdbcConn();
		List<Map> result = jdbcConn.execQuery("select p.* from sms_interface_mp_param m , sms_param p where m.param_code = p.param_code and m.inf_code = 'send'"); // send Ϊ����keyֵ���ݿ�key�����޸�
		NameValuePair[] nameValuePair = new NameValuePair[result.size()];
		for(int i = 0 ; i<result.size();i++){
			Map map = result.get(i);
			String paramValue = map.get("param_value").toString();
			if(map.get("code").toString().equals("sjhm")){
				paramValue = mobile;
			}else if(map.get("code").toString().equals("fsnr")){
				paramValue = message;
			}
			nameValuePair[i] = new NameValuePair(map.get("param_key").toString(),paramValue);
		}
		return nameValuePair;
	}*/
}
