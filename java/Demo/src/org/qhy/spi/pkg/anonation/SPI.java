package org.qhy.spi.pkg.anonation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
public @interface SPI {
	String value() default "";
}
