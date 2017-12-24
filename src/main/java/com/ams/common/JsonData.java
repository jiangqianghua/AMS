package com.ams.common;

import java.util.HashMap;
import java.util.Map;

public class JsonData {

	private boolean ret ;
	
	private String msg = null ; 
	
	private Object data = null;
	
	public JsonData(boolean ret){
		this.ret = ret;
	}
	
	public static JsonData success(Object obj,String msg){
		JsonData jsonData = new JsonData(true);
		jsonData.data = obj ; 
		jsonData.msg = msg ;
		return jsonData ;
	}
	
	public static JsonData success(String msg){
		JsonData jsonData = new JsonData(true);
		jsonData.msg = msg ; 
		return jsonData ;
	}
	
	public static JsonData success(){
		JsonData jsonData = new JsonData(true);
		return jsonData ;
	}
	
	public static JsonData fail(Object obj,String msg){
		JsonData jsonData = new JsonData(false);
		jsonData.data = obj ; 
		jsonData.msg = msg ;
		return jsonData ;
	}
	
	public static JsonData fail(Object obj){
		JsonData jsonData = new JsonData(false);
		jsonData.data = obj ; 
		return jsonData ;
	}
	public static JsonData fail(){
		JsonData jsonData = new JsonData(false);
		return jsonData ;
	}
	
	public Map<String, Object> toMap(){
		HashMap<String, Object> result = new HashMap<String, Object>();
		result.put("ret", ret);
		result.put("msg", msg);
		result.put("data", data);
		return result ;
	}

	public boolean isRet() {
		return ret;
	}

	public void setRet(boolean ret) {
		this.ret = ret;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
	
	
}
