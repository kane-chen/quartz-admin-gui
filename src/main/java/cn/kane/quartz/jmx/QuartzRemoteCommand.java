package cn.kane.quartz.jmx;

import javax.management.MBeanServerConnection;
import javax.management.ObjectName;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class QuartzRemoteCommand {

	private Long schedulerId ;
	private String[] signature;
	private String operation;
	private Object[] parameters;
	private MBeanServerConnection connection ;
	private ObjectName objectName;
	
	public QuartzRemoteCommand(Long schedulerId,String[] signature,String operation,Object[] parameters,ObjectName objectName,MBeanServerConnection connection){
		this.schedulerId = schedulerId ;
		this.signature = signature ;
		this.operation = operation ;
		this.parameters = parameters ;
		this.objectName = objectName ;
		this.connection = connection ;
	}
	
	public QuartzRemoteCommand(Long schedulerId,String[] signature,String operation,Object[] parameters){
		this.schedulerId = schedulerId ;
		this.signature = signature ;
		this.operation = operation ;
		this.parameters = parameters ;
		this.objectName = QuartzMbeanConnFactory.getObjectName(this.getSchedulerId())  ;
		this.connection = QuartzMbeanConnFactory.getMBeanConnection(this.getSchedulerId()) ; ;
	}
	
	public Long getSchedulerId() {
		return schedulerId;
	}

	public String[] getSignature() {
		return signature;
	}

	public String getOperation() {
		return operation;
	}

	public Object[] getParameters() {
		return parameters;
	}

	public ObjectName getObjectName() {
		return objectName;
	}
	
	public MBeanServerConnection getConnection() {
		return connection;
	}
	
	public void setConnection(MBeanServerConnection connection) {
		this.connection = connection;
	}
	
	@Override
	public String toString(){
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE) ;
	}
}
