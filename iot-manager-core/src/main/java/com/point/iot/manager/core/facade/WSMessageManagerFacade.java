package com.point.iot.manager.core.facade;

import java.util.Map;

/**
 * @author langxianwei
 * 
 */
public interface WSMessageManagerFacade {
	Map<Integer, WSMessageManagerLogicHandler> getFacadeMap();
}
