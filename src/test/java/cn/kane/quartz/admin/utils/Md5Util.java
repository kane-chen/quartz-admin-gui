package cn.kane.quartz.admin.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Md5Util {

	private static final Logger LOGGER = LoggerFactory.getLogger(Md5Util.class);
	
	 /**
     * MD5加密，不为空的参数String按顺序连接，并加上KEY后获得散列串
     * @param datium 要加密的参数序列
     * @return
     * @throws NoSuchAlgorithmException
     * @throws UnsupportedEncodingException
     */
    public static final String md5Encrypted(String key, Map<String,String> params)
		throws NoSuchAlgorithmException, UnsupportedEncodingException {
		StringBuilder srcBuffer = new StringBuilder("");
		srcBuffer.append(key);
		if(params != null){
			srcBuffer.append("api_key").append(params.get("api_key"))
				.append("server_id").append(params.get("server_id"))
				.append("timestamp").append(params.get("timestamp"))
				.append("user_id").append(params.get("user_id"));
		}
		System.out.println(srcBuffer.toString());
		LOGGER.info("[MD5] source-string is: {}", srcBuffer.toString());
		MessageDigest md = MessageDigest.getInstance("MD5");
		md.update(srcBuffer.toString().getBytes("UTF-8"));
		byte[] mdbytes = md.digest();
		
		int pos;
		StringBuffer encryptedBuffer = new StringBuffer();
		for (int offset = 0; offset < mdbytes.length; offset++) {
			pos = mdbytes[offset];
			if (pos < 0){
				pos += 256;
			}
			if (pos < 16) {
				encryptedBuffer.append("0");
			}
			encryptedBuffer.append(Integer.toHexString(pos));
		}
		LOGGER.info("[MD5] encrypted-string is {}",encryptedBuffer.toString());
		return encryptedBuffer.toString();
    }
    
    public static void main(String[] args) throws NoSuchAlgorithmException, UnsupportedEncodingException{
    	String key = "a002" ;
    	Map<String,String> params = new HashMap<String,String>() ;
    	params.put("api_key", "a001") ;
    	params.put("server_id", "s1") ;
    	params.put("timestamp", "2012-07-17 12:12:12") ;
    	params.put("user_id", "110") ;
    	System.out.println(md5Encrypted(key,params));
    	
    	//encode
    	String roleName = "%e6%96%af%e5%9d%a6%e5%88%a9%e6%9c%97%e6%95%a6" ;
    	roleName = java.net.URLDecoder.decode(roleName,"UTF-8"); 
    	System.out.println(roleName);
    }
	
}
