package com.point.plat.protocol.plugin.tcp;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Comparator;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.springframework.stereotype.Component;

import com.point.iot.base.message.TcpMessage;
import com.point.iot.base.message.TcpMessageResp;
import com.point.iot.base.tools.CommUtils;
import com.point.iot.manager.core.annotation.ID;
import com.point.iot.manager.core.facade.MessageDistributorLogicHandler;
import com.point.iot.manager.core.facade.MessageManagerLogicHandler;
import com.point.plat.protocol.plugin.tcp.message.handler.TcpMessageFacade;

@Component
public class TcpMessageHandler implements MessageDistributorLogicHandler {

	Logger logger = Logger.getLogger(TcpMessageHandler.class);


	@Resource(type = TcpMessageFacade.class)
	private TcpMessageFacade facade;
	
	public void doExec(TcpMessage message, IoSession session) {
		handleMsgPack(message, session); 
	}
	
	public void handleMsgPack(TcpMessage message, IoSession session){
		if(facade.getFacadeMap() != null){
			MessageManagerLogicHandler handler = (MessageManagerLogicHandler) facade.getFacadeMap().get(message.getCmd());
			
			if(handler != null){
				try {
					parse(handler, message);
				} catch (InstantiationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				TcpMessageResp resp = handler.doExec(session);
				if ( resp != null ){
					try {
						encode(session, resp);
					} catch (IllegalArgumentException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
	}
	private void encode(IoSession session, TcpMessageResp resp) throws IllegalArgumentException, IllegalAccessException{
		IoBuffer buf = IoBuffer.allocate(100);
		buf.put(resp.encode());
		Class clazz = resp.getClass();
		Field[] fields = clazz.getDeclaredFields();
		for(Field field : sortField(fields)){
			ID id = field.getAnnotation(ID.class);
			String dataType = id.dataType();
			field.setAccessible(true);
			if ( field.get(resp) == null ){
				//TODO 值为空异常处理， 说明赋值有问题
				continue;
			}
			if ( dataType.equalsIgnoreCase("byte")){
				buf.put(field.getByte(resp));
			}else if ( dataType.equalsIgnoreCase("short")){
				buf.putShort(field.getShort(resp));
			}else if ( dataType.equalsIgnoreCase("int")){
				buf.putInt(field.getInt(resp));
			}else if ( dataType.equalsIgnoreCase("long")){
				buf.putLong(field.getLong(resp));
			}else if ( dataType.equalsIgnoreCase("string")){
				Object obj = field.get(resp);
				byte[] bytes = String.valueOf(obj).getBytes();
				buf.get(bytes);
			}
		}
		buf.flip();
		session.write(buf);
	}
	/**
	 * 解析数据体
	 * @param handler
	 * @param message
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	public void parse(MessageManagerLogicHandler handler, TcpMessage message) throws InstantiationException, IllegalAccessException{
		Field[] fields = handler.getClass().getDeclaredFields();
		Class clazz = null;
		Field dataObj = null;
		for(Field field : fields){
			Class<?> superClass = field.getType().getSuperclass();
			if (superClass == TcpMessage.class){
				clazz = field.getType();
				dataObj = field;
				break;
			}
		}
		Object obj = clazz.newInstance();
		Field[] subfields = clazz.getDeclaredFields();
		//构造buffer， 准备解析
		IoBuffer buf = IoBuffer.allocate(200);
		buf.put(message.getData());
		buf.flip();
		for (Field field : sortField(subfields)) {
			ID id = field.getAnnotation(ID.class);
			if(id != null ){
				setValue(obj, field, id, buf);
			}
		}
		//数据对象赋值
		dataObj.setAccessible(true);
		dataObj.set(handler, obj);
		
	}
	/**
	 * 解析并赋值
	 * @param obj
	 * @param field
	 * @param id
	 * @param buf
	 * @throws IllegalAccessException 
	 * @throws IllegalArgumentException 
	 */
	private void setValue(Object obj, Field field, ID id, IoBuffer buf) throws IllegalArgumentException, IllegalAccessException{
		String dataType = id.dataType();
		field.setAccessible(true);
		if ( dataType.equalsIgnoreCase("byte")){
			field.set(obj, buf.get());
		}else if ( dataType.equalsIgnoreCase("short")){
			field.set(obj, buf.getShort());
		}else if ( dataType.equalsIgnoreCase("unsignedShort")){
			field.set(obj, buf.getUnsignedShort());
		}else if ( dataType.equalsIgnoreCase("int")){
			field.set(obj, buf.getInt());
		}else if ( dataType.equalsIgnoreCase("unsignedInt")){
			field.set(obj, buf.getUnsignedInt());
		}else if ( dataType.equalsIgnoreCase("long")){
			field.set(obj, buf.getLong());
		}else if ( dataType.equalsIgnoreCase("string")){
			byte[] bytes = new byte[id.lenth()];
			buf.get(bytes);
			field.set(obj, CommUtils.toHexString(bytes));
		}else if ( dataType.equalsIgnoreCase("bytes")){
			byte[] bytes = new byte[id.lenth()];
			buf.get(bytes);
			field.set(obj, bytes);
		}
	}
	
	/**
	 * 按照注解中order顺序从小到大排序属性
	 * @param fields
	 * @return
	 */
	public Field[] sortField(Field[] fields){  
        Arrays.sort(fields,new Comparator<Field>() {  
            @Override  
            public int compare(Field o1, Field o2) {  
            	ID id1 = o1.getAnnotation(ID.class);
            	ID id2 = o1.getAnnotation(ID.class);
                return id1.order()>id2.order()?0:1;  
            }  
        });
		return fields;  
	}
        
}
