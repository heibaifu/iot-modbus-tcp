package com.point.plat.admin.plugin.tcp.message.handler;

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
import com.point.plat.admin.plugin.codec.AdminMessageTcpDecoder;
import com.point.plat.admin.plugin.codec.AdminMessageTcpEncoder;
import com.point.plat.admin.plugin.tcp.AdminTcpNetCmd;

@Component
public class AdminTcpMessageFacade implements MessageManagerFacade {
	Logger logger = Logger.getLogger(AdminTcpMessageFacade.class);

	private Map<Integer, MessageManagerLogicHandler> facadeMap = new HashMap<Integer, MessageManagerLogicHandler>();
	@Resource(type = RemoteControlMessageClientHandler.class)
	private RemoteControlMessageClientHandler remoteControlMessageClientHandler;
	
	@PostConstruct
	public void registry() {
		logger.info("====================AdminMessageFacade Registry=======================");
		//注册解码器及编码器
		MessageCodecRegister.addEncoder(PointMessage.class, new AdminMessageTcpEncoder());
		MessageCodecRegister.addDecoder(PointMessage.class, new AdminMessageTcpDecoder());
		// TCP协议解析组件
		this.facadeMap.put(AdminTcpNetCmd.SET_ALERT_TEMP, remoteControlMessageClientHandler);
	}

	@Override
	public Map<Integer, MessageManagerLogicHandler> getFacadeMap() {
		return facadeMap;
	}

}
