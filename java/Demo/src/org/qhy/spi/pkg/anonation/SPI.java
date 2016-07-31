package org.qhy.spi.pkg.anonation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Retention(RetentionPolicy.RUNTIME) //反射机制需要保留到运行阶段
@Target({ElementType.TYPE})
public @interface SPI {
	String value();
}
