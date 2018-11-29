package com.point.plat.admin.plugin.tcp;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.point.iot.manager.core.facade.MessageDistributorFacade;
import com.point.iot.manager.core.facade.MessageDistributorLogicHandler;
import com.point.iot.utils.Constant;

@Component
public class AdminTcpProtocolFacade implements MessageDistributorFacade {

	private Map<Integer, MessageDistributorLogicHandler> facadeMap = new HashMap<Integer, MessageDistributorLogicHandler>();

	@Autowired
	private AdminTcpMessageHandler adminTcpMessageHandler;

	@PostConstruct
	public void registry() {
		// // 透传客户端的MsgPack消息
		facadeMap.put((int) Constant.ADMIN_TCP, adminTcpMessageHandler);
	}

	public Map<Integer, MessageDistributorLogicHandler> getFacadeMap() {
		return facadeMap;
	}

}
