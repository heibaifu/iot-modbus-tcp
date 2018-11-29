package com.point.plat.protocol.plugin.tcp.message.handler;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.point.iot.base.message.MessageCodecRegister;
import com.point.iot.base.message.PointMessage;
import com.point.iot.manager.core.facade.MessageManagerFacade;
import com.point.iot.manager.core.facade.MessageManagerLogicHandler;
import com.point.plat.protocol.plugin.codec.MessageTcpDecoder;
import com.point.plat.protocol.plugin.codec.MessageTcpEncoder;
import com.point.plat.protocol.plugin.tcp.TcpNetCmd;

@Component
public class TcpMessageFacade implements MessageManagerFacade {
	Logger logger = Logger.getLogger(TcpMessageFacade.class);

	private Map<Byte, MessageManagerLogicHandler> facadeMap = new HashMap<Byte, MessageManagerLogicHandler>();
	@Resource(type = LoginMessageClientHandler.class)
	private LoginMessageClientHandler loginMessageClientHandler;
	
	@PostConstruct
	public void registry() {
		logger.info("====================TCPMessageFacade Registry=======================");
		//注册解码器及编码器
		MessageCodecRegister.addEncoder(PointMessage.class, new MessageTcpEncoder());
		MessageCodecRegister.addDecoder(PointMessage.class, new MessageTcpDecoder());
		// TCP协议解析组件
		this.facadeMap.put(TcpNetCmd.LOGIN_ID, loginMessageClientHandler);
	}

	@Override
	public Map<Byte, MessageManagerLogicHandler> getFacadeMap() {
		return facadeMap;
	}

}
