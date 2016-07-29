package org.qhy.nio.netty.server;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.DatagramPacket;

import java.util.Random;

public class UdpServerHandler extends SimpleChannelInboundHandler<DatagramPacket> {

    private static final Random random = new Random();

    // Quotes from Mohandas K. Gandhi:
    private static final String[] quotes = {
        "Where there is love there is life.",
        "First they ignore you, then they laugh at you, then they fight you, then you win.",
        "Be the change you want to see in the world.",
        "The weak can never forgive. Forgiveness is the attribute of the strong.",
    };

    private static String nextQuote() {
        int quoteId;
        synchronized (random) {
            quoteId = random.nextInt(quotes.length);
        }
        return quotes[quoteId];
    }

    @Override
    public void channelRead0(ChannelHandlerContext ctx, DatagramPacket packet) throws Exception {
    	ByteBuf buf = packet.content();
    	byte[] req = new byte[buf.readableBytes()];
    	buf.readBytes(req);
    	String body = new String(req,"UTF-8");
    	System.out.println("接收到AP数据:>>>>"+body);
//        System.err.println(packet);
        
//        if ("QOTM?".equals(packet.content().toString(CharsetUtil.UTF_8))) {
//            ctx.write(new DatagramPacket(
//                    Unpooled.copiedBuffer("QOTM: " + nextQuote(), CharsetUtil.UTF_8), packet.sender()));
//        }
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        // We don't close the channel because we can keep serving requests.
    }
}

