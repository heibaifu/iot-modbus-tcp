

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
5. 使用tcp测试工具 发送消息68 14 00 00 68 80 00 64 16 



6. 演示地址：
    [后台管理端](https://gitee.com/pointiot/iot-remote)