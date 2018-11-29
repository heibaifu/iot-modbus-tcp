package com.point.plat.protocol.plugin.codec;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Comparator;

import org.apache.log4j.Logger;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;
import org.apache.mina.filter.codec.demux.MessageEncoder;
import org.springframework.stereotype.Component;

import com.point.iot.base.message.PointMessage;
import com.point.iot.manager.core.annotation.ID;
/**
 * 采集仪消息编码器
 * @author lenovo
 *
 */
@Component
public class MessageTcpEncoder implements MessageEncoder<PointMessage> {
	Logger logger = Logger.getLogger(MessageTcpEncoder.class);
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
	/**
	 * 解析并赋值
	 * @param obj
	 * @param field
	 * @param id
	 * @param buf
	 * @throws IllegalAccessException 
	 * @throws IllegalArgumentException 
	 */
	private void setValue(IoBuffer buff, ID id, Object value) throws IllegalArgumentException, IllegalAccessException{
		String dataType = id.dataType();
		if ( dataType.equalsIgnoreCase("byte")){
			buff.put((Byte) value);
		}else if ( dataType.equalsIgnoreCase("short")){
			buff.putShort((Short)value);
		}else if ( dataType.equalsIgnoreCase("unsignedShort")){
			buff.putUnsignedShort((Short)value);
		}else if ( dataType.equalsIgnoreCase("int")){
			buff.putInt((Integer)value);
		}else if ( dataType.equalsIgnoreCase("unsignedInt")){
			buff.putUnsignedInt((Integer)value);
		}else if ( dataType.equalsIgnoreCase("long")){
			buff.putLong((Long)value);
		}else if ( dataType.equalsIgnoreCase("string")){
			byte[] bytes = String.valueOf(value).getBytes();
			buff.put(bytes);
		}else if ( dataType.equalsIgnoreCase("bytes")){
			buff.put(toByteArray(value));
		}
	}
    /**  
     * 对象转数组  
     * @param obj  
     * @return  
     */  
    public byte[] toByteArray (Object obj) {      
        byte[] bytes = null;      
        ByteArrayOutputStream bos = new ByteArrayOutputStream();      
        try {        
            ObjectOutputStream oos = new ObjectOutputStream(bos);         
            oos.writeObject(obj);        
            oos.flush();         
            bytes = bos.toByteArray ();      
            oos.close();         
            bos.close();        
        } catch (IOException ex) {        
            ex.printStackTrace();   
        }      
        return bytes;    
    }   
	@Override
	public void encode(IoSession session, PointMessage message, ProtocolEncoderOutput out) throws Exception {
		IoBuffer buf = IoBuffer.allocate(100).setAutoExpand(true);  
		//协议簇
		buf.put(message.getProtocolType());
		//命令号
		buf.put(message.getCmd());
		Class<? extends PointMessage> clazz = message.getClass();
		Field[] subfields = clazz.getDeclaredFields();
		for (Field field : sortField(subfields)) {
			ID id = field.getAnnotation(ID.class);
			field.setAccessible(true);
			if(id != null ){
				setValue(buf, id, field.get(message));
			}
		}
		logger.info("发送下行消息：protocolType=" + message.getProtocolType() + ", 命令：" + message.getCmd());
		buf.flip();
		out.write(buf);
	}

}
