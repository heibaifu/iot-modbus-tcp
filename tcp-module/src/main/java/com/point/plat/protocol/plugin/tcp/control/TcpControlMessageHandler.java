package com.point.plat.protocol.plugin.tcp.control;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.ObjectMessage;
import javax.jms.TextMessage;

import org.springframework.stereotype.Component;

import com.point.iot.manager.core.control.ControlMessageHandler;
@Component
public class TcpControlMessageHandler implements ControlMessageHandler {

	@Override
	public void doExec(MapMessage message) {
		
		try {
			System.out.println("message:" + message.getString("key"));
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void doExec(TextMessage message) {
		// TODO Auto-generated method stub

	}

	@Override
	public void doExec(ObjectMessage message) {
		// TODO Auto-generated method stub

	}

	@Override
	public String getProtocolType() {
		return "tcp";
	}

}
