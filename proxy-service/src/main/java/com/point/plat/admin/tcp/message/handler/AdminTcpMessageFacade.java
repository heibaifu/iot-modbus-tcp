package com.point.plat.admin.tcp.message.handler;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.point.iot.base.message.MessageCodecRegister;
import com.point.iot.manager.core.facade.MessageManagerFacade;
import com.point.iot.manager.core.facade.MessageManagerLogicHandler;
import com.point.plat.admin.codec.AdminMessageTcpDecoder;
import com.point.plat.admin.codec.AdminMessageTcpEncoder;
import com.point.plat.admin.tcp.AdminTcpNetCmd;
import com.point.plat.admin.tcp.model.AdminTcpMessage;

@Component
public class AdminTcpMessageFacade implements MessageManagerFacade {
	Logger logger = Logger.getLogger(AdminTcpMessageFacade.class);

	private Map<Byte, MessageManagerLogicHandler> facadeMap = new HashMap<Byte, MessageManagerLogicHandler>();
	@Resource(type = RemoteControlMessageClientHandler.class)
	private RemoteControlMessageClientHandler remoteControlMessageClientHandler;
	
	@PostConstruct
	public void registry() {
		logger.info("====================AdminMessageFacade Registry=======================");
		//注册解码器及编码器
		MessageCodecRegister.addEncoder(AdminTcpMessage.class, new AdminMessageTcpEncoder());
		MessageCodecRegister.addDecoder(AdminTcpMessage.class, new AdminMessageTcpDecoder());
		// TCP协议解析组件
		this.facadeMap.put(AdminTcpNetCmd.SET_ALERT_TEMP, remoteControlMessageClientHandler);
	}

	@Override
	public Map<Byte, MessageManagerLogicHandler> getFacadeMap() {
		return facadeMap;
	}

}
