package com.point.iot.base.message;

import org.apache.mina.core.buffer.IoBuffer;

public class TcpMessageResp {
	
	//协议类型
	private int protocolType;
	
	// 表示命令号，下述功能中已给具体命令号
	private int cmd;
	
	private byte[] data;
	
	public byte[] getData() {
		return data;
	}
	public void setData(byte[] data) {
		this.data = data;
	}
	public int getCmd() {
		return cmd;
	}
	public void setCmd(int cmd) {
		this.cmd = cmd;
	}
	public int getProtocolType() {
		return protocolType;
	}
	public void setProtocolType(int protocolType) {
		this.protocolType = protocolType;
	}
	
	public IoBuffer encode(){
		IoBuffer buf = IoBuffer.allocate(10);
		buf.putInt(this.protocolType);
		buf.putInt(this.cmd);
		buf.flip();
		return buf;
	}
}
