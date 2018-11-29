package com.point.plat.admin.tcp.message.handler;

import java.util.Iterator;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.mina.core.session.IoSession;
import org.springframework.stereotype.Component;

import com.point.iot.base.message.TcpMessageResp;
import com.point.iot.manager.core.facade.MessageManagerLogicHandler;
import com.point.iot.service.proxy.ProxyBiz;
import com.point.iot.utils.Constant;
import com.point.plat.admin.tcp.model.RemoteControlRequestBean;
import com.point.plat.protocol.plugin.tcp.TcpNetCmd;
import com.point.plat.protocol.plugin.tcp.model.TemperatureSetRequestBean;
/**
 * 远控消息逻辑类
 * @author langxianwei 2017年3月18日
 *
 */
@Component
public class RemoteControlMessageClientHandler implements MessageManagerLogicHandler{
	Logger logger = Logger.getLogger(RemoteControlMessageClientHandler.class);
	
	private RemoteControlRequestBean remoteControlRequestBean;
	
	public void setremoteControlRequestBean(RemoteControlRequestBean remoteControlRequestBean) {
		this.remoteControlRequestBean = remoteControlRequestBean;
	}

	public TcpMessageResp doExec(IoSession session) {
		logger.info("收到远控指令，控制设备ID:[" + remoteControlRequestBean.getDeviceId() + "]" );
		Map<Long, IoSession> sessionMap = ProxyBiz.getInst().getSessionMap();
		
		Iterator<IoSession> it = sessionMap.values().iterator();
		while(it.hasNext()) {
			IoSession clientSession = it.next();
			if ( clientSession != session ) {
				TemperatureSetRequestBean temperatureSetRequestBean = new TemperatureSetRequestBean();
				temperatureSetRequestBean.setProtocolType(Constant.CAIJIYI_TCP);
				temperatureSetRequestBean.setCmd(TcpNetCmd.SET_TEMP_ID);
				temperatureSetRequestBean.setDeviceId(remoteControlRequestBean.getDeviceId());
				temperatureSetRequestBean.setMaxTemperature(remoteControlRequestBean.getMaxTemperature());
				temperatureSetRequestBean.setMinTemperature(remoteControlRequestBean.getMinTemperature());
				clientSession.write(temperatureSetRequestBean);
			}
		}
		return null;
	}

}
