package org.qhy.nio.nettyserver;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.FixedLengthFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

import java.net.InetSocketAddress;

public class FixdLengthNettyServer {
	private static final int SOCKET_PORT = 18888;
	private static final int LENGTH = 512;
	private static EventLoopGroup bossGroup = null;
	private static EventLoopGroup workerGroup = null;
	public static void startSocketServer(){
		System.out.println("启动netty服务...");
		 bossGroup = new NioEventLoopGroup();
		 workerGroup = new NioEventLoopGroup();
		try {
			ServerBootstrap sbtp = new ServerBootstrap();
			sbtp.group(bossGroup, workerGroup)
					.channel(NioServerSocketChannel.class)
					.handler(new LoggingHandler(LogLevel.INFO))
					.childHandler(new ChannelInitializer<SocketChannel>() {
						@Override
						protected void initChannel(SocketChannel channel)
								throws Exception {
							ChannelPipeline p = channel.pipeline();
							p.addLast(new FixedLengthFrameDecoder(LENGTH));
							p.addLast(new StringDecoder());
							p.addLast(new ObjectEchoServerHandler());
						}
					});
			sbtp.bind(new InetSocketAddress(SOCKET_PORT)).sync().channel().closeFuture().sync();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	public static void closeSokectServer(){
		System.out.println("关闭netty服务...");
		if(bossGroup != null){
			bossGroup.shutdownGracefully();
		}
		if(workerGroup != null){
			workerGroup.shutdownGracefully();
		}
		
	}
	public static void main(String[] args) throws Exception {
//		byte[] aa= DECOLLATOR.getBytes();
//		byte[] bb = new byte[]{98,-26,-22,-34};
////		System.out.println(new String(bb,Charset.forName("UTF-8")));
//		System.out.println("14 f2 02 01 02 00 00 00 00 00 00 02 b7 a7 00 02".length());
		startSocketServer();
	}
}
