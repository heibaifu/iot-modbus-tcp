package com.point.plat.protocol.plugin.tcp.message.handler;

import org.apache.log4j.Logger;
import org.apache.mina.core.session.IoSession;
import org.springframework.stereotype.Component;

import com.point.iot.base.message.TcpMessageResp;
import com.point.iot.manager.core.facade.MessageManagerLogicHandler;
import com.point.plat.protocol.plugin.tcp.model.LoginRequestBean;
import com.point.plat.protocol.plugin.tcp.model.LoginResponseBean;
/**
 * 登录处理逻辑类
 * 收到登录消息后，发送登录响应消息。同时，发送发送控制码为0x04的帧召测数据
 * @author langxianwei 2017年3月18日
 *
 */
@Component
public class LoginMessageClientHandler implements MessageManagerLogicHandler{
	Logger logger = Logger.getLogger(LoginMessageClientHandler.class);
	
	private LoginRequestBean loginRequestBean;
	
	public void setLoginRequestBean(LoginRequestBean loginRequestBean) {
		this.loginRequestBean = loginRequestBean;
	}

	public TcpMessageResp doExec(IoSession session) {
		logger.info("收到设备" + loginRequestBean.getDeviceId() + "的登录消息" );
		LoginResponseBean loginResponseBean = new LoginResponseBean();
		loginResponseBean.setProtocolType(loginRequestBean.getProtocolType());
		loginResponseBean.setCmd(loginRequestBean.getCmd()|0xFF00);
		loginResponseBean.setDeviceType(loginRequestBean.getDeviceType());
		loginResponseBean.setDeviceId(loginRequestBean.getDeviceId());
		return loginResponseBean;
	}

}
