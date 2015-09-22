package com.sms.junit;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class JunitTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String keyChar = "replys=";
		String resultStr = "result=0&confirm_time=2015-01-28 15:37:29&id=2102849182&replys=[{\"callmdn\":\"10690559777406\",\"mdn\":\"18811726825\",\"content\":\"djdjdjjd\",\"reply_time\":\"2015-01-28 13:03:07\",\"id\":\"2102848461\"},{\"callmdn\":\"10690559777406\",\"mdn\":\"18811726825\",\"content\":\"һëǮ\",\"reply_time\":\"2015-01-28 13:05:42\",\"id\":\"2102848474\"},{\"callmdn\":\"10690559777406\",\"mdn\":\"15210831566\",\"content\":\"654321\",\"reply_time\":\"2015-01-28 15:17:56\",\"id\":\"2102849085\"},{\"callmdn\":\"10690559777406\",\"mdn\":\"15210831566\",\"content\":\"0225\",\"reply_time\":\"2015-01-28 15:37:29\",\"id\":\"2102849182\"}]";
		String replyStr = resultStr.substring(resultStr.indexOf(keyChar) + keyChar.length(), resultStr.length());
		System.out.println(replyStr);
		String replyData = replyStr.substring(replyStr.indexOf("[") + 1, replyStr.lastIndexOf("]"));
		List<Map> param = new ArrayList<Map>();
		Map map = new HashMap();
		map.put("zh", "123");
		map.put("mm", "456");
		param.add(map);
		map = new HashMap();
		map.put("zh", "aaa");
		map.put("mm", "bbb");
		param.add(map);
		JSONArray data = JSONArray.fromObject(replyStr);
		for (int i = 0; i < data.size(); i++) {
			JSONObject jobj = (JSONObject) data.get(i);
			String name = (String) jobj.get("content");
			System.out.println(name);
		}
		//System.out.println(data1.toString());
		// [{"mm":"456","zh":"123"},{"mm":"bbb","zh":"aaa"}]
		// JSONArray data = JSONArray.fromObject(a);
		/*
		 * for (String str : replyData.split("},")) { str =
		 * str.replaceAll("\\u007B", ""); str = str.replaceAll(",\\u007B", "");
		 * str = str.replaceAll("}", ""); for (String str2 : str.split(",")) {
		 * if(str2.indexOf("\"callmdn\"")>-1){
		 * System.out.println((str2.substring(10,
		 * str2.length())).replaceAll("\"","")); }else
		 * if(str2.indexOf("\"content\"")>-1){ System.out.println(
		 * str2.substring(10,str2.length())); }else
		 * if(str2.indexOf("\"reply_time\"")>-1){ System.out.println(
		 * str2.substring(13,str2.length())); }else
		 * if(str2.indexOf("\"mdn\"")>-1){
		 * System.out.println(str2.substring(6,str2.length())); }else
		 * if(str2.indexOf("\"id\"")>-1){
		 * System.out.println(str2.substring(5,str2.length())); } }
		 * System.out.println("------------------------"); } }
		 */

	}
}