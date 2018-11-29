package com.point.iot.manager.core.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 在属性上加上此注解表示该属性需要序列化
 * @author sunchong
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface ID {
	/**
	 * 定义字段的顺序
	 * @return
	 */
	byte order();
	/**
	 * 定义字段所占字节数
	 * @return
	 */
	byte lenth();
	/**
	 * 数据类型
	 * @return
	 */
	String dataType();
}
