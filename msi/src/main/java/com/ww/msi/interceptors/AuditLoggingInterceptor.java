package com.ww.msi.interceptors;

import java.util.HashMap;
import java.util.Map;

import org.mule.api.MuleContext;
import org.mule.api.MuleEvent;
import org.mule.api.MuleException;
import org.mule.api.annotations.expressions.Lookup;
import org.mule.api.construct.FlowConstruct;
import org.mule.api.construct.FlowConstructAware;
import org.mule.api.context.MuleContextAware;
import org.mule.api.interceptor.Interceptor;
import org.mule.interceptor.LoggingInterceptor;
import org.mule.management.stats.ProcessingTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;




public class AuditLoggingInterceptor extends LoggingInterceptor implements Interceptor,FlowConstructAware,MuleContextAware
	
{ 


@Override
    public void setFlowConstruct(FlowConstruct flowConstruct) {
    	this.flowConstruct=flowConstruct;
  
	}
	
	@Lookup 
	private MuleContext muleContext; 

	public void setMuleContext(MuleContext muleContext) { 
	System.out.println("setMuleContext"); 
	this.muleContext = muleContext; 
	
	} 
	
	java.sql.Timestamp date1 = new java.sql.Timestamp(new java.util.Date().getTime());
	public java.sql.Timestamp getDate1() {
		return date1;
	}
   
	
    
	
	private final static Logger logger = LoggerFactory.getLogger(
			AuditLoggingInterceptor.class);
	
		@Override
		public MuleEvent before(MuleEvent event) {
			try {
				
				
				
				logger.info("[START FLowname-]"+event.getFlowConstruct().getName()+""+"::[DATE]"+date1 + " "+"::[RequestId]"+event.getMessage().getPayload()+":: [Operation]" +" " +"::[UserId]"+" "+"::[Payload]"+" ");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		
			String requestid=(String)event.getMessage().getPayload();
			java.sql.Timestamp date1 = new java.sql.Timestamp(new java.util.Date().getTime());
			System.out.println(date1);
			String operationtype=null;
			String userid=null;
		
			
			Map<String,Object> m=new HashMap<String,Object>();
		
			m.put(Constants.MSI_REQUESTID, requestid);
			m.put(Constants.MSI_DATE, date1);
			m.put(Constants.MSI_OPERATION, operationtype);
			m.put(Constants.MSI_USERID, userid);
		
		
			
			try {
				muleContext.getClient().send("Audit_vm",m,null);
			} catch (MuleException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			return (event);
		}

		@Override
		public MuleEvent after(MuleEvent event) {
			try {
				logger.info("[After  FLowname]"+event.getFlowConstruct().getName()+"::[DATE]"+date1 + " "+"::[ResponseId ]"+event.getMessage().getPayload()+":: [Operation]" +" " +"::[UserId]"+" "+"::[Payload]"+" ");
				} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return event;
		}

		@Override
		public MuleEvent last(MuleEvent event, ProcessingTime time, long startTime,
				boolean exceptionWasThrown) throws MuleException {
			//logger.info("[END FLowname-]"+event.getFlowConstruct().getName()+""+"::[DATE]"+date1 + " "+"::"+":: [Operation]" +" " +"::[UserId]"+" "+"::[Payload]"+" ");
			try {
				logger.info("last call + "
						+ event.getMessage().getPayloadAsString());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return event;
		}

		
	}

	
