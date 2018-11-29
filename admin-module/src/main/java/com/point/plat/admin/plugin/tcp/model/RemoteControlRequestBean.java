package com.point.plat.admin.plugin.tcp.model;

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
	@ID(order=2, lenth=4, dataType="int")
	private int maxTemperature;
	/**
	 * 低温报警溫度
	 */
	@ID(order=2, lenth=4, dataType="int")
	private int minTemperature;
	
	public int getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(int deviceId) {
		this.deviceId = deviceId;
	}
	public int getMaxTemperature() {
		return maxTemperature;
	}
	public void setMaxTemperature(int maxTemperature) {
		this.maxTemperature = maxTemperature;
	}
	public int getMinTemperature() {
		return minTemperature;
	}
	public void setMinTemperature(int minTemperature) {
		this.minTemperature = minTemperature;
	}
	
}
