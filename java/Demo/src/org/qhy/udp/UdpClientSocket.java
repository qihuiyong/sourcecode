package org.qhy.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UdpClientSocket {  
    private byte[] buffer = new byte[1024];  
  
    private static DatagramSocket ds = null;  
      
    /** 
     * 测试客户端发包和接收回应信息的方法 
     */  
    public static void main(String[] args) throws Exception {  
        UdpClientSocket client = new UdpClientSocket();  
        String serverHost = "127.0.0.1";  
        int serverPort = 8888;  
        client.send(serverHost, serverPort, ("11 334F dd1111111 ee").getBytes());  
        byte[] bt = client.receive();  
        System.out.println("服务端回应数据：" + new String(bt));  
        // 关闭连接  
        try {  
            ds.close();  
        } catch (Exception ex) {  
            ex.printStackTrace();  
        }  
    }  
  
    /** 
     * 构造函数，创建UDP客户端 
     */  
    public UdpClientSocket() throws Exception {  
        ds = new DatagramSocket(8899); // 邦定本地端口作为客户端  
    }  
      
    /** 
     * 向指定的服务端发送数据信息 
     */  
    public final void send(final String host, final int port,  
            final byte[] bytes) throws IOException {
    	for(int i=0;i<10;i++){
    		 DatagramPacket dp = new DatagramPacket(bytes, bytes.length, InetAddress.getByName(host), port);  
    	        ds.send(dp); 
    	}
        
    }  
  
    /** 
     * 接收从指定的服务端发回的数据 
     */  
    public final byte[] receive()  
            throws Exception {  
        DatagramPacket dp = new DatagramPacket(buffer, buffer.length);  
        ds.receive(dp);       
        byte[] data = new byte[dp.getLength()];  
        System.arraycopy(dp.getData(), 0, data, 0, dp.getLength());       
        return data;  
    }  
}  
