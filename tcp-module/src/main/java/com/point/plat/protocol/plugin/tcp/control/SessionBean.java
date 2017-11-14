package com.point.plat.protocol.plugin.tcp.control;

import java.util.HashMap;
import java.util.Map;

import org.apache.mina.core.session.IoSession;

public class SessionBean {

	private static Map<Integer, IoSession> sessionMap = new HashMap<Integer, IoSession>();
	/**
	 * 管理session
	 * @param code
	 * @param session
	 */
	public static void putSession(int code, IoSession session){
		sessionMap.put(code, session);
	}
	/**
	 * 删除session
	 * @param code
	 */
	public static void delSession(int code){
		if ( sessionMap .containsKey(code)){
			sessionMap.remove(code);
		}
	}
	/**
	 * 根据设备编码获取对应的session
	 * @param code
	 * @return
	 */
	public static IoSession getSession(int code){
		return sessionMap.get(code);
	}
	
}
