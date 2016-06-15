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
//			System.in.read(bytes);
			channel = SocketChannel.open();
			channel.connect(address);
			for(int i=0;i<1000;i++){
				buffer.clear();
				buffer.put("��dd���Ƿ��ǵ������ǵ�������ssdffffffffffff�ǵ�������ȷ��ĵ�����ʿ���dd��&33b2&33c3&33".getBytes("UTF-8"));
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
		client.scaninSubmitToServer("localhost", 8007);
	}

}
