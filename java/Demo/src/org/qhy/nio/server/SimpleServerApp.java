
package org.qhy.nio.server;


import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.channels.spi.SelectorProvider;
import java.util.Set;

public class SimpleServerApp {

    public static void main(String[] args) throws Exception {
        Selector selector = SelectorProvider.provider().openSelector();
        ServerSocketChannel channel = ServerSocketChannel.open();
        channel.configureBlocking(false);
        channel.socket().bind(new InetSocketAddress("localhost", 7765));
        channel.register(selector, SelectionKey.OP_ACCEPT);
        selector.select();
        Set<SelectionKey> keySet = selector.selectedKeys();
        for (SelectionKey key : keySet) {
            if(!key.isValid()){continue;}
            if(key.isReadable()){
                ByteBuffer buffer = ByteBuffer.allocate(512);
               SocketChannel socketChannel = (SocketChannel)key.channel();
               socketChannel.read(buffer);
               System.out.println(new String(buffer.array()));
            }else if(key.isAcceptable()){
                ServerSocketChannel serverChannel = (ServerSocketChannel)key.channel();
                serverChannel.accept();
                serverChannel.configureBlocking(false);
                serverChannel.register(selector, SelectionKey.OP_READ);
            }
        }
    }
}
