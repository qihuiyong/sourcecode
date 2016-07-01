package org.qhy.nio.nettyserver;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

import java.net.InetSocketAddress;

public class NettyServer {
	private static final int SOCKET_PORT = 8007;
	private static final String SOCKET_HOST ="127.0.0.1";
	private static final String DECOLLATOR ="02 00 00 00 00 00 00 00 00 00 00 00 00";
	public static boolean startSocketServer(){
		boolean result =false;
		EventLoopGroup bossGroup = new NioEventLoopGroup();
		EventLoopGroup workerGroup = new NioEventLoopGroup();
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
							ByteBuf delimiter = Unpooled.copiedBuffer(DECOLLATOR.getBytes());
							p.addLast(new DelimiterBasedFrameDecoder(1024, delimiter));
							p.addLast(new StringDecoder());
							p.addLast(new ObjectEchoServerHandler());
						}
					});
			sbtp.bind(new InetSocketAddress(SOCKET_HOST,SOCKET_PORT)).sync().channel().closeFuture().sync();
			result=true;
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			bossGroup.shutdownGracefully();
			workerGroup.shutdownGracefully();
			return result;
		}
	}
	public static void main(String[] args) throws Exception {
//		byte[] aa= DECOLLATOR.getBytes();
//		byte[] bb = new byte[]{98,-26,-22,-34};
////		System.out.println(new String(bb,Charset.forName("UTF-8")));
//		System.out.println("14 f2 02 01 02 00 00 00 00 00 00 02 b7 a7 00 02".length());
		if(startSocketServer()){
			System.out.println("socket启动成功!");
		}else{
			System.out.println("socket启动失败!!!");
		}
	}
}
