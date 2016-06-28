package org.qhy.nio.client;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class SocketClient {
	//创建缓冲区
	ByteBuffer buffer = ByteBuffer.allocate(2048);

	public void scaninSubmitToServer(String host,int port) throws IOException {
		InetSocketAddress address = new InetSocketAddress(host, port);
		SocketChannel channel = null;
		byte[] bytes= new byte[1024];
		//读控制台输入懂啊bytes
		System.out.print("请输入数据:");
		try {
//			System.in.read(bytes);
			channel = SocketChannel.open();
			channel.connect(address);
			for(int i=0;i<10;i++){
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				buffer.clear();
				String input ="SS 00 RR 11 22 33 55 67 00 RR 11 22 33 55 67 00 RR 11 22 33 55 67 00 RR 11 22 33 55 67 00 RR 11 22 33 55 6722 33 55 67 00 RR 11 22 33 55 67 00 RR 11 22 33 55 67 00 RR 11 22 33 55 67 00 RR 11 22 33 55 6722 33 55 67 00 RR 11 22 33 55 67 00 RR 11 22 33 55 67 00 RR 11 22 33 55 67 00 RR 11 22 33 55 6722 33 55 67 00 RR 11 22 33 55 67 00 RR 11 22 33 55 67 00 RR 11 22 33 55 67 00 RR 11 22 33 55 6722 33 55 67 00 RR 11 22 33 55 67 00 RR 11 22 33 55 67 00 RR 11 22 33 55 67 00 RR 11 22 33 55 67 SSSSSSSSSSSSSSSSSSSSSSSSSSSSSSEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE";
				buffer.put(input.getBytes());
				buffer.flip();
				channel.write(buffer);
			}
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
//		client.scaninSubmitToServer("localhost", 9099); AppServer
		client.scaninSubmitToServer("localhost", 8888);
	}

}
