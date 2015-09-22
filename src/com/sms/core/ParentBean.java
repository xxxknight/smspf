package com.sms.core;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.httpclient.NameValuePair;
import org.apache.cxf.message.Message;
import org.apache.cxf.phase.PhaseInterceptorChain;
import org.apache.cxf.transport.http.AbstractHTTPDestination;

public class ParentBean {
	public String getClientIp() {
		Message msa = PhaseInterceptorChain.getCurrentMessage();
		HttpServletRequest httprequest = (HttpServletRequest) msa.get(AbstractHTTPDestination.HTTP_REQUEST);
		return httprequest.getRemoteAddr();
	}
	
	public String getParamStr(NameValuePair[] nvps){
		StringBuilder paramStr = new StringBuilder("[");
		for(NameValuePair nvp : nvps){
			paramStr.append(nvp.getName()+"="+nvp.getValue()+",");
		}
		paramStr.append("]");
		return paramStr.toString();
	}
}
