package cn.kane.quartz.jmx;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import javax.management.MBeanServerConnection;
import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



public class QuartzMbeanConnFactory {

	private static Logger logger = LoggerFactory.getLogger(QuartzMbeanConnFactory.class) ;
	private static final String schedulerJmxObjectName="*:type=QuartzScheduler\\,*" ;
	private static Map<Long,MBeanServerConnection> quartzMBeanConns = new ConcurrentHashMap<Long, MBeanServerConnection>() ;
	private static Map<Long,ObjectName> objNames = new ConcurrentHashMap<Long, ObjectName>() ;
	
	
	public static MBeanServerConnection connQuartzScheduler(Long schedulerId,String host,int port,String uname,String password,String schdName) throws IOException, MalformedObjectNameException{
		MBeanServerConnection connection = quartzMBeanConns.get(schedulerId) ;
		if(null!=connection){
			return connection ;
		}
		//connection
		Map<String, String[]> env = new HashMap<String, String[]>();
		env.put(JMXConnector.CREDENTIALS, new String[] { uname, password });
		JMXServiceURL jmxServiceURL = createQuartzInstanceConnection(host,port);
		JMXConnector connector = JMXConnectorFactory.connect(jmxServiceURL, env);
		connection = connector.getMBeanServerConnection();
		//scheduler-JMX-objectName
		ObjectName mBName = new ObjectName(schedulerJmxObjectName);
		Set<ObjectName> names = connection.queryNames(mBName, null);
		for (ObjectName objectName : names) {  // for each scheduler.
			logger.info("MBeans: " + objectName.getCanonicalName());
			if(null == schdName || objectName.getCanonicalName().equals(schdName)){//TODO toCheck
				quartzMBeanConns.put(schedulerId, connection) ;
				objNames.put(schedulerId, objectName) ;
				break ;
			}
		}
		
		return connection;
	}
	
	public static boolean isConnect(Long schedulerId){
		boolean isConnect = false ;
		MBeanServerConnection connection = quartzMBeanConns.get(schedulerId) ;
		if(null!=connection){
			try {
				connection.getMBeanCount() ;
				isConnect = true ;
			} catch (IOException e) {
				logger.error("check-connecting:{} ERR:{}",schedulerId,e);
			}
		}
		return isConnect ;
	}
	
	public static MBeanServerConnection getMBeanConnection(Long schedulerId){
		return quartzMBeanConns.get(schedulerId) ;
	}
	
	public static ObjectName getObjectName(Long schedulerId){
		return objNames.get(schedulerId) ;
	}
	
	private static JMXServiceURL createQuartzInstanceConnection(String host,int port)throws MalformedURLException {
		StringBuffer stringBuffer = new StringBuffer().append("service:jmx:rmi:///jndi/rmi://")
				.append(host).append(":").append(port)
				.append("/jmxrmi");
		JMXServiceURL jmxServiceURL = new JMXServiceURL(stringBuffer.toString());
		return jmxServiceURL;
	}
	
	public static void disMBeanConn(Long schedulerId){
		quartzMBeanConns.remove(schedulerId) ;
		objNames.remove(schedulerId) ;
	}
}
