

轻型高性能物联网通信框架，适用于多种场景；支持TCP通讯方式。 

- 稳定、高并发，单服务器轻松支持8000+终端； 
- JAVA语言开发，可移植，支持Windows，Linux平台部署； 
- 采用优雅的松耦合架构，支持Spring； 
- 支持多编解码器；支持协议解析组件热插拔； 
- Web管理端支持多种报表展示方式。 
- 支持关系型数据库、内存数据库、实时数据库存储。

QQ群：157973669
 ----------

# 使用手册 #
## 搭建开发环境 ##
1. 下载Eclipse  
	http://www.eclipse.org/downloads/eclipse-packages/

>             Eclipse IDE for Java Developers
> 		Package Description
> 		The essential tools for any Java developer, including a Java IDE, a Git client, XML Editor, Mylyn, Maven and Gradle integration
> 		
> 		This package includes:
> 			Git integration for Eclipse
> 			Eclipse Java Development Tools
> 			Maven Integration for Eclipse
> 			Mylyn Task List
> 			Code Recommenders Tools for Java Developers
> 			Eclipse XML Editors and Tools
2. 使用Git下载源码到本地
3. 导入源码到Eclipse中，使用Maven导入工程，具体搜索百度
4. 启动服务程序
	在Eclipse中运行com.point.iot.service.proxy.ProxyServiceMain
     发现运行日志且无报错，说明程序运行正常：
```
     INFO [TcpMessageFacade.java:34] - ====================TcpMessageFacade Registry=======================
     INFO [SocketServer.java:51] - 服务器启动正常，监听端口 65001
```

5.测试程序

```
public class TcpClient{

 	public static void main(String args[]) {
 		//注册解码器及编码器
 		MessageCodecRegister.addEncoder(CjyTcpMessage.class, new MessageTcpEncoder());
 		MessageCodecRegister.addDecoder(CjyTcpMessage.class, new MessageTcpDecoder());
 		SocketClient client = new SocketClient("127.0.0.1", 65001);
 		//68 15 00 00 00 68 84 10 2F 03 00 00 BE 01 00 00 D6 F9 FF FF A6 FA FF FF D5 16
 		byte data[] = new byte[]{(byte)0x68,(byte)0x15,(byte)0x00,(byte)0x00,
 		(byte)0x00,(byte)0x68,(byte)0x80,(byte)0x00,(byte)0x65,(byte)0x16};
 		IoBuffer buf = IoBuffer.allocate(100).setAutoExpand(true);
 		buf.put(data);
 		buf.flip();
 		client.send(buf);
 	}
 }
```
	
