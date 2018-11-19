package com.point.plat.protocol.plugin.codec;

import org.apache.log4j.Logger;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;
import org.apache.mina.filter.codec.demux.MessageDecoder;
import org.apache.mina.filter.codec.demux.MessageDecoderResult;
import org.springframework.stereotype.Component;

import com.point.iot.base.message.TcpMessage;
import com.point.iot.base.tools.MessageUtil;
@Component
public class MessageTcpDecoder implements MessageDecoder{
	Logger logger = Logger.getLogger(MessageTcpDecoder.class) ;
	@Override
	public MessageDecoderResult decodable(IoSession session, IoBuffer buf) {
		return MessageDecoderResult.OK;
	}

	@Override
	public MessageDecoderResult decode(IoSession session, IoBuffer buf, ProtocolDecoderOutput out) throws Exception {
		TcpMessage message = new TcpMessage();
		logger.info("收到上行消息" + buf.getHexDump());
		//协议簇
		message.setProtocolType(buf.getInt());
		//命令号
		message.setCmd(buf.getInt());
		int len = buf.remaining();
		//数据体
		byte[] data = MessageUtil.getBytes(buf, len);
		message.setData(data);
		out.write(message);
		return MessageDecoderResult.OK;
	}

	@Override
	public void finishDecode(IoSession session, ProtocolDecoderOutput out) throws Exception {
		// TODO Auto-generated method stub
		
	}

}
