package com.point.plat.protocol.plugin.tcp;

public class TcpNetCmd {
    /**
     * 应答
     */
    public static final byte ACK = 0x0f;
    /**
     * 登录消息
     */
	public static final byte LOGIN_ID = 0x00;
	/**
	 * 召测数据
	 */
	public static final byte READ_DATA_ID = 0x04;
	/**
	 * 时间设置
	 */
	public static final byte SET_TIME_ID = 0x03;
	/**
	 * 退出登录
	 */
	public static final byte LOGOUT_ID = 0x01;
	/**
	 * 参数设置
	 */
	public static final byte SET_PARAM_ID = 0x02;
	/**
	 * 温度设置
	 */
	public static final byte SET_TEMP_ID = 0x05;
}
