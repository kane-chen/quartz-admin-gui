package cn.kane.quartz.admin.utils;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpClientUtils {
	
	private final static Logger LOGGER = LoggerFactory.getLogger(HttpClientUtils.class) ;
	
	private static final String CHARSET_UTF8 = "UTF-8" ; 
	/** socket-timeout,unit:milliseconds */
	private static int  soTimeout = 5000 ; 
	/** request-timeout,unit:milliseconds */
	private static int reqTimeout = 3000 ;

	public static String call(String url,Map<String,String> params,boolean isJson) throws Exception{
		String resp = null ;
		
		CloseableHttpClient httpClient = HttpClientBuilder.create().build() ;
		//params
		List<NameValuePair> paramPairs = new ArrayList<NameValuePair>() ;
		if(null!=params){
			for(String key : params.keySet()){
				paramPairs.add(new BasicNameValuePair(key, params.get(key))) ;
			}
		}
		//execute
        try {
        	HttpPost httpPost = new HttpPost(url) ;
        	//config(timeout)
        	RequestConfig reqConfig =  RequestConfig.custom()  
        		    .setConnectTimeout(soTimeout)  
        		    .setSocketTimeout(reqTimeout)
        		    .build();   ;
		    httpPost.setConfig(reqConfig);
        	//header
        	UrlEncodedFormEntity entity;
            entity = new UrlEncodedFormEntity(paramPairs, CHARSET_UTF8);
            httpPost.setEntity(entity);
            if(isJson){
            	httpPost.addHeader("Content-Type", "application/json");
//            	httpPost.addHeader("Accept","application/json");
            }
            //execute
            LOGGER.info("[HTTP] POST-REQUEST:url={},params={}",url,params);
            HttpResponse httpResponse = httpClient.execute(httpPost);
            //response
            HttpEntity httpEntity = httpResponse.getEntity();
            if (httpEntity != null) {
            	resp = EntityUtils.toString(httpEntity, CHARSET_UTF8) ;
            }
        } catch (Exception e) {
        	LOGGER.error("Call Error",e);
        	throw new Exception(e) ;
        }finally{
        	//release
        	try{
        		httpClient.close();
        	}catch(IOException e){
        		LOGGER.error("httpClient release-resource error",e);
        	}
        }
        LOGGER.info("[HTTP] POST-RESPONSE:{}",resp);
        return resp ;
	}

	public static String jsonCall(String url,String jsonParams) throws Exception{
		String resp = null ;
		
		CloseableHttpClient httpClient = HttpClientBuilder.create().build() ;
		//execute
        try {
        	HttpPost httpPost = new HttpPost(url) ;
        	//config(timeout)
        	RequestConfig reqConfig =  RequestConfig.custom()  
        		    .setConnectTimeout(soTimeout)  
        		    .setSocketTimeout(reqTimeout)
        		    .build();   ;
		    httpPost.setConfig(reqConfig);
		    httpPost.addHeader("Content-Type", "application/json;charset=UTF-8");
        	httpPost.addHeader("Accept","application/json");
        	//header
        	HttpEntity entity = new StringEntity(jsonParams);
            httpPost.setEntity(entity);
            //execute
            LOGGER.info("[HTTP] POST-JSON-REQUEST:url={},params={}",url,jsonParams);
            HttpResponse httpResponse = httpClient.execute(httpPost);
            //response
            HttpEntity httpEntity = httpResponse.getEntity();
            if (httpEntity != null) {
            	resp = EntityUtils.toString(httpEntity, CHARSET_UTF8) ;
            }
        } catch (Exception e) {
        	LOGGER.error("JSONCall Error",e);
        	throw new Exception(e) ;
        }finally{
        	//release
        	try{
        		httpClient.close();
        	}catch(IOException e){
        		LOGGER.error("httpClient release-resource error",e);
        	}
        }
        LOGGER.info("[HTTP] POST-JSON-RESPONSE:{}",resp);
        return resp ;
	}
	
	public static String get(String url,Map<String,String> params) throws Exception{
		String resp = null ;
		
		CloseableHttpClient httpClient = HttpClientBuilder.create().build() ;
		//params
		StringBuilder paramBuffer = new StringBuilder() ;
		if(null!=params){
			for(String key : params.keySet()){
				String encodeKey = URLEncoder.encode(key,CHARSET_UTF8);
				String encodeValue = URLEncoder.encode(params.get(key),CHARSET_UTF8);
				paramBuffer.append(encodeKey).append("=").append(encodeValue).append("&") ;
			}
		}
		String paramInStr = paramBuffer.toString() ;
		if(StringUtils.isNotBlank(paramInStr)){
			paramInStr = "?"+paramInStr ;
		}
		if(StringUtils.endsWith(paramInStr, "&")){
			paramInStr = paramInStr.substring(0, paramInStr.length()-1) ;
		}
		//execute
		try {
			String targetUrl = url +paramInStr ;
			LOGGER.info("[HTTP] Get-REQUEST:{}",targetUrl);
			HttpGet httpGet = new HttpGet(targetUrl) ;
			//config(timeout)
			RequestConfig reqConfig =  RequestConfig.custom()  
					.setConnectTimeout(soTimeout)  
					.setSocketTimeout(reqTimeout)
					.build();   ;
			httpGet.setConfig(reqConfig);
			//execute
			HttpResponse httpResponse = httpClient.execute(httpGet);
			//response
			HttpEntity httpEntity = httpResponse.getEntity();
			if (httpEntity != null) {
				resp = EntityUtils.toString(httpEntity, CHARSET_UTF8) ;
			}
		} catch (Exception e) {
			LOGGER.error("Call Error",e);
			throw new Exception(e) ;
		}finally{
			//release
			try{
				httpClient.close();
			}catch(IOException e){
				LOGGER.error("httpClient release-resource error",e);
			}
		}
		LOGGER.info("[HTTP] Get-RESPONSE:{}",resp);
		return resp ;
	}
	
}
