package org.qhy.nio.netty.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;

public class NettyClient {
	static final String HOST = "127.0.0.1";
    static final int PORT = 8007;
    static final int SIZE = 256;
	public static void main(String[] args) throws InterruptedException {
		 EventLoopGroup group = new NioEventLoopGroup();
	        try {
	            Bootstrap b = new Bootstrap();
	            b.group(group)
	             .channel(NioSocketChannel.class)
	             .handler(new ChannelInitializer<SocketChannel>() {
	                @Override
	                public void initChannel(SocketChannel ch) throws Exception {
	                    ChannelPipeline p = ch.pipeline();
	                    p.addLast(
	                            new ObjectEncoder(),
	                            new ObjectDecoder(ClassResolvers.cacheDisabled(null)),
	                            new ObjectEchoClientHandler());
	                }
	             });

	            // Start the connection attempt.
	            b.connect(HOST, PORT).sync().channel().closeFuture().sync();
	        } finally {
	            group.shutdownGracefully();
	        }
	}
}
