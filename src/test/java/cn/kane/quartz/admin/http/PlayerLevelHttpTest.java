package cn.kane.quartz.admin.http;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import cn.kane.quartz.admin.utils.HttpClientUtils;
import cn.kane.quartz.admin.utils.Md5Util;
import junit.framework.TestCase;

public class PlayerLevelHttpTest extends TestCase{

	private String url = "http://car.open.9211.com/baidu/player" ;
	private String api_key = "4acdeadc014a7e9ad2b5256ef247914d" ;
	private String secret_key = "8a97a7b24acdeadc014af247914d256f" ;
	private DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss") ;
	
	public void testGet() throws Exception{
		String userId = "71935748" ;
		String serverId = "1" ;
		String timestamp = df.format(new Date()) ;
		Map<String,String> params = new HashMap<String,String>() ;
		params.put("user_id", userId);
		params.put("server_id", serverId);
		params.put("timestamp", timestamp);
		params.put("api_key", api_key);
		String sign = Md5Util.md5Encrypted(secret_key,params) ;
		params.put("sign", sign);
		String resp = HttpClientUtils.get(url, params) ;
		resp = java.net.URLDecoder.decode(resp,"UTF-8"); 
		System.out.println(resp);
	}
	
	
//	
}
