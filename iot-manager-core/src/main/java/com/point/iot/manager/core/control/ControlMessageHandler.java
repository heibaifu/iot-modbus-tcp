package com.point.iot.manager.core.control;

import javax.jms.MapMessage;
import javax.jms.ObjectMessage;
import javax.jms.TextMessage;

/**
 * @author langxianwei
 * 
 */
public interface ControlMessageHandler {
	/**
	 * 返回消息协议簇
	 * @return
	 */
	public String getProtocolType();
	/**
	 * 处理MapMessage消息接口
	 * @param message
	 */
	public void doExec(MapMessage message);
	/**
	 * 处理TextMessage文本消息接口
	 * @param message
	 */
	public void doExec(TextMessage message);
	
	/**
	 * 处理ObjectMessage消息接口
	 * @param message
	 */
	public void doExec(ObjectMessage message);
}
