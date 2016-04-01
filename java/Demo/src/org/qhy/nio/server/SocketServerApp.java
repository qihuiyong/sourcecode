package org.qhy.nio.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.channels.spi.SelectorProvider;
import java.util.Iterator;

public class SocketServerApp implements Runnable{
	//定义两个端口
	private int prot1=8099,port2=9099;
	//定义两个服务器通道
	private ServerSocketChannel serversocket1,serversocket2;
	//定义两个连接
	private SocketChannel clientchannel1,clientchannel2;
	//选择器，用于监控各个通道的事件
	private Selector selector; 
	//缓冲区
	private ByteBuffer byteBuffer = ByteBuffer.allocate(512); 
	
	public SocketServerApp(){
		this.init();
	}
	public void init(){
		try {
			//创建选择器
			this.selector = SelectorProvider.provider().openSelector();
			
			
			//创建服务器通道1
			this.serversocket1 = ServerSocketChannel.open();
			//告诉程序现在不是阻塞方式
			this.serversocket1.configureBlocking(false);
			//获取现在与该通道关联的套接字
			this.serversocket1.socket().bind(new InetSocketAddress("localhost", prot1));
			/**
			 * 将选择器注册到通道上，返回一个选择键
			 * OP_ACCEPT用于套接字接受操作的操作集位   
			 */
			this.serversocket1.register(this.selector, SelectionKey.OP_ACCEPT);
			
			
			//初始化第二个服务通道(与第一个一样)
			this.serversocket2 = ServerSocketChannel.open();
			this.serversocket2.configureBlocking(false);
			this.serversocket2.socket().bind(new InetSocketAddress("localhost", port2));
			this.serversocket2.register(this.selector, SelectionKey.OP_ACCEPT);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 该方法用于客户端连接服务器
	 * @param selectionKey
	 * @throws IOException
	 */
	public void accept(SelectionKey selectionKey) throws IOException{
		ServerSocketChannel server = (ServerSocketChannel)selectionKey.channel();
		if(server.equals(serversocket1)){
			this.clientchannel1 = server.accept();
			this.clientchannel1.configureBlocking(false);
			this.clientchannel1.register(this.selector, SelectionKey.OP_READ);
		}else{
			this.clientchannel2 = server.accept();
			this.clientchannel2.configureBlocking(false);
			this.clientchannel2.register(this.selector, SelectionKey.OP_READ);
		}
	}
	/**
	 * 从通道中读取数据
	 * @param selectionKey
	 * @throws IOException
	 */
	
	public void read(SelectionKey selectionKey) throws IOException{
		this.byteBuffer.clear();
		//通过选择键找到之前注册的通道
		SocketChannel channel = (SocketChannel)selectionKey.channel();
		int count = channel.read(byteBuffer);
		
		if(count == -1){
			selectionKey.channel().close();
			selectionKey.cancel();
			return;
		}
		String input = new String(this.byteBuffer.array()).trim();
		
		//判断使用的哪个通道
		if(channel.equals(this.clientchannel1)){
			System.out.println("欢迎您使用服务A");  
            System.out.println("您的输入为：" + input);  
		}else{
			System.out.println("欢迎您使用服务B");  
            System.out.println("您的输入为：" + input);  
		}
		
	}
	@Override
	public void run() {
		while (true) { 
			System.out.println("running ... ");
			try {
				//选择一组键，其相应的通道已为 I/O 操作准备就绪。 
				this.selector.select();
				System.out.println("running ... 11111111111");
				//返回此选择器的已选择键集
				Iterator<SelectionKey> selectorKeys = this.selector.selectedKeys().iterator();
				while(selectorKeys.hasNext()){
					 System.out.println("running2 ... 222222222222");
					 //找到当前键的选择键
					 SelectionKey key = selectorKeys.next();
					 //然后将他从返回键队列删除
					 selectorKeys.remove();
					 if(!key.isValid()){//选择键无效
						 continue;
					 }
					 if(key.isAcceptable()){
						 //如果遇到请求那么就响应  
					     System.out.println("isAcceptable");
						 this.accept(key);
					 }else if(key.isReadable()){
					     System.out.println("isReadable");
						 //读取客户端数据
						 this.read(key);
					 }
					 
				}
				
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
	}
	
	public static void main(String[] args) {
		SocketServerApp server = new SocketServerApp();
		Thread thread = new Thread(server);
		thread.run();
	}
	
	
}
