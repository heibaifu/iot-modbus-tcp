package com.point.plat.protocol.plugin.tcp.control;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.ObjectMessage;
import javax.jms.TextMessage;

import org.apache.mina.core.session.IoSession;
import org.springframework.stereotype.Component;

import com.point.iot.manager.core.control.ControlMessageHandler;
import com.point.plat.protocol.plugin.tcp.model.CjyTcpMessage;
@Component
public class TcpControlMessageHandler implements ControlMessageHandler {
	/**
	 * 解析MapMessage 并发送控制消息到设备
	 */
	@Override
	public void doExec(MapMessage message) {
		
		try {
			int code = message.getInt("code");
			IoSession session = SessionBean.getSession(code);
			if ( session != null ){
				CjyTcpMessage cjyTcpMessage = new CjyTcpMessage();
				cjyTcpMessage.setAddress(code);
				cjyTcpMessage.setCmd(message.getInt("cmd"));
				cjyTcpMessage.setData(message.getBytes("data"));
				session.write(cjyTcpMessage);
			}
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
