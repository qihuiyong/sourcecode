package org.qhy.spi.api;

import org.qhy.spi.pkg.anonation.SPI;

/**
 * @author qihuiyong
 *
 */
@SPI(value = "echo1")
public interface IEcho {
	public void echo();
}

