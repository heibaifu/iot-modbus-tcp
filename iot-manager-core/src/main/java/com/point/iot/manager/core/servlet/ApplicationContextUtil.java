package com.point.iot.manager.core.servlet;

import java.util.Map;

import javax.jms.JMSException;
import javax.jms.MapMessage;

import org.apache.mina.core.session.IoSession;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.point.iot.base.message.TcpMessage;
import com.point.iot.manager.core.control.ControlMessageHandler;
import com.point.iot.manager.core.facade.MessageManagerFacade;
import com.point.iot.manager.core.facade.MessageManagerLogicHandler;

public class ApplicationContextUtil {
	
	private static ApplicationContext context;
	private static Object lock=new Object();
	
	public static void registor() {
		synchronized (lock) {
			if (context == null) {
				context = new ClassPathXmlApplicationContext("yahe-manager-spring.xml");  
			}
		}
	}
	/**
	 * 获取远程控制消息处理类
	 * @return
	 * @throws JMSException 
	 */
	public static void CallControlMessageHandler(MapMessage message) throws JMSException{
		Map<String, ControlMessageHandler>  map = context.getBeansOfType(ControlMessageHandler.class);
		for(Map.Entry<String, ControlMessageHandler> entry : map.entrySet()){
			ControlMessageHandler control = entry.getValue();
			if(message != null && control.getProtocolType().equals(message.getString("protocolType"))){
				control.doExec(message);
			}
		}
	}
	
	/**
	 * 转发消息
	 * @param message
	 * @param session
	 */
	public  static void callIotRequestProvider(TcpMessage message, IoSession session){
		MessageManagerLogicHandler handler = null;
		Map<String, MessageManagerFacade> map = context.getBeansOfType(MessageManagerFacade.class);
		for(Map.Entry<String, MessageManagerFacade> entry : map.entrySet()){
			MessageManagerFacade facade = entry.getValue();
			if(facade.getFacadeMap() != null){
				handler = (MessageManagerLogicHandler) facade.getFacadeMap().get(message.getProtocolType());
				if(handler != null){
					 handler.doExec(message, session);
				}
			}
		}
	}

	public static ApplicationContext getContext() {
		return context;
	}

	public static void setContext(ApplicationContext context) {
		ApplicationContextUtil.context = context;
	}

}
