package org.qhy.nio.netty.server;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioDatagramChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

public class UdpServer {

    private static final int PORT = 8888;
    private static EventLoopGroup group = new NioEventLoopGroup();
    public static void startServer(){
            Bootstrap b = new Bootstrap();
            b.group(group)
             .channel(NioDatagramChannel.class)
             .handler(new LoggingHandler(LogLevel.INFO))
             .option(ChannelOption.SO_BROADCAST, true)
             .handler(new UdpServerHandler());

            try {
				b.bind(PORT).sync().channel().closeFuture().await();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
    }
    public static void stopServer(){
    	group.shutdownGracefully();
    }
}

