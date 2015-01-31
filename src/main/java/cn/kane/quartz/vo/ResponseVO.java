package cn.kane.quartz.vo;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class ResponseVO implements Serializable {

	/**
	 * UID
	 */
	private static final long serialVersionUID = 125938933501750769L;

	private int retCode ;
	private String message ;
	private Object respObj ;
	
	public int getRetCode() {
		return retCode;
	}


	public void setRetCode(int retCode) {
		this.retCode = retCode;
	}


	public String getMessage() {
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
	}


	public Object getRespObj() {
		return respObj;
	}


	public void setRespObj(Object respObj) {
		this.respObj = respObj;
	}
	
	@Override
	public String toString(){
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE) ;
	}
	
	
	public enum RetStatus{
		SUCCESS(200,"OK"),
		FAILED(500,"FAILED");
		private RetStatus(int retCode,String msg) {
			this.retCode = retCode ;
			this.message = msg ;
		}
		public int getRetCode(){
			return this.retCode;
		}
		public String getMessage(){
			return this.message ;
		}
		int retCode ;
		String message ;
	}
}
