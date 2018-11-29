package com.point.iot.manager.core.facade;

import org.apache.mina.core.session.IoSession;

import com.point.iot.base.message.PointMessage;


/**
 * @author langxianwei
 * 
 */
public interface MessageDistributorLogicHandler {

	void doExec(PointMessage message,IoSession session);
}
