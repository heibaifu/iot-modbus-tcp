package com.point.plat.protocol.plugin.tcp.model;

import com.point.iot.base.message.TcpMessage;
import com.point.iot.manager.core.annotation.ID;

public class LoginRequestBean extends TcpMessage{
	/**
	 * 设备类型
	 */
	@ID(order=1, lenth=1, dataType="byte")
	private byte deviceType;
	/**
	 * 设备ID
	 */
	@ID(order=2, lenth=4, dataType="int")
	private int deviceId;
	public byte getDeviceType() {
		return deviceType;
	}
	public void setDeviceType(byte deviceType) {
		this.deviceType = deviceType;
	}
	public int getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(int deviceId) {
		this.deviceId = deviceId;
	}
	
}
