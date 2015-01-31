package cn.kane.quartz.admin.utils;

import com.alibaba.fastjson.JSON;

public class JsonUtils {

	public static String objectToJson(Object object) {
		return JSON.toJSONString(object);
	}
	
	public  static <T> T json2Object(String jsonStr,Class<T> clazz){
		return JSON.parseObject(jsonStr,clazz) ;
	}
	
}
