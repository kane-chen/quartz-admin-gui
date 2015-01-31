package cn.kane.quartz.jmx;

import java.util.Date;

import javax.management.MBeanServerConnection;
import javax.management.ObjectName;
import javax.management.openmbean.CompositeDataSupport;

import org.apache.commons.lang3.StringUtils;

public class JmxRemoteExecutor {


	public static boolean isSupported(String version) {
		return StringUtils.isNotEmpty(version) && version.startsWith("2");
	}

	public static Object callJMXAttribute(QuartzRemoteCommand command) throws Exception {
		MBeanServerConnection connection = command.getConnection() ;
		return (Object) connection.getAttribute(command.getObjectName(), command.getOperation());
	}

	public static Object callJMXOperation(QuartzRemoteCommand command) throws Exception {
		MBeanServerConnection connection = command.getConnection() ;
		ObjectName objName = command.getObjectName() ;
		return connection.invoke(objName, command.getOperation(),
				command.getParameters(), command.getSignature());
	}

	public static Object convertToType(CompositeDataSupport compositeDataSupport, String key) {
		if (compositeDataSupport.getCompositeType().getType(key).getClassName()
				.equals("java.lang.String")) {
			return StringUtils.trimToEmpty((String) compositeDataSupport.get(key));
		} else if (compositeDataSupport.getCompositeType().getType(key).getClassName()
				.equals("java.lang.Boolean")) {
			return compositeDataSupport.get(key);
		} else if (compositeDataSupport.getCompositeType().getType(key).getClassName()
				.equals("java.util.Date")) {
			return (Date) compositeDataSupport.get(key);
		} else if (compositeDataSupport.getCompositeType().getType(key).getClassName()
				.equals("java.lang.Integer")) {
			return (Integer) compositeDataSupport.get(key);
		} else if (compositeDataSupport.getCompositeType().getType(key).getClassName()
				.equals("java.lang.Long")) {
			return (Long) compositeDataSupport.get(key);
		}
		return new Object();
	}
}