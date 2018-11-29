package com.point.plat.admin.tcp.model;

import com.point.iot.base.message.PointMessage;
import com.point.iot.manager.core.annotation.ID;

public class RemoteControlRequestBean extends PointMessage{
	/**
	 * 设备ID
	 */
	@ID(order=2, lenth=4, dataType="int")
	private int deviceId;
	/**
	 * 高温报警溫度
	 */
	@ID(order=2, lenth=2, dataType="short")
	private short maxTemperature;
	/**
	 * 低温报警溫度
	 */
	@ID(order=2, lenth=2, dataType="short")
	private short minTemperature;
	
	public int getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(int deviceId) {
		this.deviceId = deviceId;
	}
	public short getMaxTemperature() {
		return maxTemperature;
	}
	public void setMaxTemperature(short maxTemperature) {
		this.maxTemperature = maxTemperature;
	}
	public short getMinTemperature() {
		return minTemperature;
	}
	public void setMinTemperature(short minTemperature) {
		this.minTemperature = minTemperature;
	}
	
}
