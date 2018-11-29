package com.point.plat.admin.codec;

import org.apache.log4j.Logger;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;
import org.apache.mina.filter.codec.demux.MessageEncoder;
import org.springframework.stereotype.Component;

import com.point.iot.base.message.PointMessage;
/**
 * 采集仪消息编码器
 * @author lenovo
 *
 */
@Component
public class AdminMessageTcpEncoder implements MessageEncoder<PointMessage> {
	Logger logger = Logger.getLogger(AdminMessageTcpEncoder.class);
	@Override
	public void encode(IoSession session, PointMessage message, ProtocolEncoderOutput out) throws Exception {
		IoBuffer buf = IoBuffer.allocate(100).setAutoExpand(true);  
		//协议簇
		buf.putInt(message.getProtocolType());
		//命令号
		buf.putInt(message.getCmd());
		//数据域
		buf.put(message.getData());
		logger.info("发送下行消息：protocolType=" + message.getProtocolType() + ", 命令：" + message.getCmd());
		out.write(buf);
	}

}
