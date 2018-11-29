package com.point.plat.admin.codec;

import org.apache.log4j.Logger;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;
import org.apache.mina.filter.codec.demux.MessageDecoder;
import org.apache.mina.filter.codec.demux.MessageDecoderResult;
import org.springframework.stereotype.Component;

import com.point.iot.base.message.PointMessage;
import com.point.iot.base.tools.MessageUtil;
import com.point.iot.utils.Constant;
@Component
public class AdminMessageTcpDecoder implements MessageDecoder{
	Logger logger = Logger.getLogger(AdminMessageTcpDecoder.class) ;
	@Override
	public MessageDecoderResult decodable(IoSession session, IoBuffer buf) {
		int len = buf.remaining();
		byte protocolType = buf.get();
		if ( protocolType != Constant.ADMIN_TCP) {
			return MessageDecoderResult.NOT_OK;
		}
		if ( len< 10) {
			return MessageDecoderResult.NEED_DATA;
		}
		return MessageDecoderResult.OK;
	}

	@Override
	public MessageDecoderResult decode(IoSession session, IoBuffer buf, ProtocolDecoderOutput out) throws Exception {
		PointMessage message = new PointMessage();
		logger.info("收到管理端消息" + buf.getHexDump());
		//协议簇
		message.setProtocolType(buf.get());
		//命令号
		message.setCmd(buf.get());
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
