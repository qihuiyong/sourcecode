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
	//���������˿�
	private int prot1=8099,port2=9099;
	//��������������ͨ��
	private ServerSocketChannel serversocket1,serversocket2;
	//������������
	private SocketChannel clientchannel1,clientchannel2;
	//ѡ���������ڼ�ظ���ͨ�����¼�
	private Selector selector; 
	//������
	private ByteBuffer byteBuffer = ByteBuffer.allocate(512); 
	
	public SocketServerApp(){
		this.init();
	}
	public void init(){
		try {
			//����ѡ����
			this.selector = SelectorProvider.provider().openSelector();
			
			
			//����������ͨ��1
			this.serversocket1 = ServerSocketChannel.open();
			//���߳������ڲ���������ʽ
			this.serversocket1.configureBlocking(false);
			//��ȡ�������ͨ���������׽���
			this.serversocket1.socket().bind(new InetSocketAddress("localhost", prot1));
			/**
			 * ��ѡ����ע�ᵽͨ���ϣ�����һ��ѡ���
			 * OP_ACCEPT�����׽��ֽ��ܲ����Ĳ�����λ   
			 */
			this.serversocket1.register(this.selector, SelectionKey.OP_ACCEPT);
			
			
			//��ʼ���ڶ�������ͨ��(���һ��һ��)
			this.serversocket2 = ServerSocketChannel.open();
			this.serversocket2.configureBlocking(false);
			this.serversocket2.socket().bind(new InetSocketAddress("localhost", port2));
			this.serversocket2.register(this.selector, SelectionKey.OP_ACCEPT);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * �÷������ڿͻ������ӷ�����
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
	 * ��ͨ���ж�ȡ����
	 * @param selectionKey
	 * @throws IOException
	 */
	
	public void read(SelectionKey selectionKey) throws IOException{
		this.byteBuffer.clear();
		//ͨ��ѡ����ҵ�֮ǰע���ͨ��
		SocketChannel channel = (SocketChannel)selectionKey.channel();
		int count = channel.read(byteBuffer);
		
		if(count == -1){
			selectionKey.channel().close();
			selectionKey.cancel();
			return;
		}
		String input = new String(this.byteBuffer.array()).trim();
		
		//�ж�ʹ�õ��ĸ�ͨ��
		if(channel.equals(this.clientchannel1)){
			System.out.println("��ӭ��ʹ�÷���A");  
            System.out.println("��������Ϊ��" + input);  
		}else{
			System.out.println("��ӭ��ʹ�÷���B");  
            System.out.println("��������Ϊ��" + input);  
		}
		
	}
	@Override
	public void run() {
		while (true) { 
			System.out.println("running ... ");
			try {
				//ѡ��һ���������Ӧ��ͨ����Ϊ I/O ����׼�������� 
				this.selector.select();
				System.out.println("running ... 11111111111");
				//���ش�ѡ��������ѡ�����
				Iterator<SelectionKey> selectorKeys = this.selector.selectedKeys().iterator();
				while(selectorKeys.hasNext()){
					 System.out.println("running2 ... 222222222222");
					 //�ҵ���ǰ����ѡ���
					 SelectionKey key = selectorKeys.next();
					 //Ȼ�����ӷ��ؼ�����ɾ��
					 selectorKeys.remove();
					 if(!key.isValid()){//ѡ�����Ч
						 continue;
					 }
					 if(key.isAcceptable()){
						 //�������������ô����Ӧ  
					     System.out.println("isAcceptable");
						 this.accept(key);
					 }else if(key.isReadable()){
					     System.out.println("isReadable");
						 //��ȡ�ͻ�������
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
