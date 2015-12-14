package org.qhy.nio.client;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class SocketClient {
	//����������
	ByteBuffer buffer = ByteBuffer.allocate(512);

	public void scaninSubmitToServer(String host,int port) throws IOException {
		InetSocketAddress address = new InetSocketAddress(host, port);
		SocketChannel channel = null;
		byte[] bytes= new byte[512];
		//������̨���붮��bytes
		System.out.print("����������:");
		try {
			System.in.read(bytes);
			channel = SocketChannel.open();
			channel.connect(address);
			buffer.clear();
			buffer.put(bytes);
			buffer.flip();
			channel.write(buffer);
			buffer.clear();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(channel != null){
				channel.close();
			}
		}
	}
	public static void main(String[] args) throws IOException {
		SocketClient client = new SocketClient();
		client.scaninSubmitToServer("localhost", 9099);
	}

}
