package com.point.iot.base.message;

import java.util.Map;

import org.apache.mina.core.session.IoSession;

public class PointMessage {
	
	//协议类型
	private byte protocolType;
	
	// 表示命令号，下述功能中已给具体命令号
	private byte cmd;
	
	private byte[] data;
	//在線設備session
	private Map<Long, IoSession> sessionMap;
	
	public byte[] getData() {
		return data;
	}
	public void setData(byte[] data) {
		this.data = data;
	}
	public byte getCmd() {
		return cmd;
	}
	public void setCmd(byte cmd) {
		this.cmd = cmd;
	}
	public byte getProtocolType() {
		return protocolType;
	}
	public void setProtocolType(byte protocolType) {
		this.protocolType = protocolType;
	}
	public Map<Long, IoSession> getSessionMap() {
		return sessionMap;
	}
	public void setSessionMap(Map<Long, IoSession> sessionMap) {
		this.sessionMap = sessionMap;
	}
	
}
