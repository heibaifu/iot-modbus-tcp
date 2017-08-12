package com.point.iot.service.proxy;

import org.apache.log4j.Logger;

public class ProxyServiceMain {
	static Logger logger = Logger.getLogger(ProxyServiceMain.class);
    public static void main(String[] args) { 
//    	FlashBiz fb = new FlashBiz();
    	logger.debug("HallService服务启动......");
		try {
			ProxyBiz.getInst().init();
			logger.debug("HallService服务启动完成!");
		} catch (Exception e) {
			logger.error("服务启动失败!", e);
		}
    } 
    
}
