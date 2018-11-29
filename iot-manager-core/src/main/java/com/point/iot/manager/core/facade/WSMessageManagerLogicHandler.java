package com.point.iot.manager.core.facade;

import org.apache.mina.core.session.IoSession;

import com.point.iot.base.message.PointMessage;


/**
 * @author langxianwei
 * 
 */
public interface WSMessageManagerLogicHandler {
	/**
	 * 返回为null 表示无需响应客户端
	 * @param session
	 * @return
	 */
	void doExec(PointMessage message, IoSession session);
}
