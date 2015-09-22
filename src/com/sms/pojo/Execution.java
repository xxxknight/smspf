package com.sms.pojo;

public class Execution {
	public int status; /* 执行状态 */
	public String result; /* 返回结果 */
	public String exception; /* 异常信息 */

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getException() {
		return exception;
	}

	public void setException(String exception) {
		this.exception = exception;
	}

}
