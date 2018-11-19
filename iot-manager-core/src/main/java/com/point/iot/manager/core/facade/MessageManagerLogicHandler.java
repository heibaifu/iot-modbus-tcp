package com.point.iot.manager.core.facade;

import org.apache.mina.core.session.IoSession;

import com.point.iot.base.message.TcpMessageResp;


/**
 * @author langxianwei
 * 
 */
public interface MessageManagerLogicHandler {
	/**
	 * 返回为null 表示无需响应客户端
	 * @param session
	 * @return
	 */
	TcpMessageResp doExec(IoSession session);
}
