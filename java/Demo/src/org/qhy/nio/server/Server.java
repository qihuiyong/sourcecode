package org.qhy.nio.server;  
  
import java.io.IOException;  
import java.net.InetSocketAddress;  
import java.nio.ByteBuffer;  
import java.nio.IntBuffer;
import java.nio.channels.SelectionKey;  
import java.nio.channels.Selector;  
import java.nio.channels.ServerSocketChannel;  
import java.nio.channels.SocketChannel;  
import java.nio.channels.spi.SelectorProvider;  
import java.util.Iterator;  
import java.util.concurrent.Callable;
  
/**    
 * TCP/IP��NIO��������ʽ   
 * ��������   
 * */  
public class Server implements Runnable {  
  
    //��һ���˿�     
    private Integer port1 = 8099;  
    //�ڶ����˿�     
    private Integer port2 = 9099;  
    //��һ��������ͨ�� ����A     
    private ServerSocketChannel serversocket1;  
    //�ڶ���������ͨ�� ����B     
    private ServerSocketChannel serversocket2;  
    //����1     
    private SocketChannel clientchannel1;  
    //����2     
    private SocketChannel clientchannel2;  
  
    //ѡ��������Ҫ������ظ���ͨ�����¼�     
    private Selector selector;  
      
    //������     
    private ByteBuffer buf = ByteBuffer.allocate(512);  
      
    public Server() {  
        init();  
    }  
  
    /**   
     * ���method������ 
     * 1���ǳ�ʼ��ѡ����   
     * 2��������ͨ��   
     * 3����ͨ���ϰ�һ��socket   
     * 4����ѡ����ע�ᵽͨ����   
     * */  
    public void init() {  
        try {  
            //����ѡ����     
            this.selector = SelectorProvider.provider().openSelector();  
            //�򿪵�һ��������ͨ��     
            this.serversocket1 = ServerSocketChannel.open();  
            //���߳������ڲ���������ʽ��     
            this.serversocket1.configureBlocking(false);  
            //��ȡ�������ͨ���������׽���     
            this.serversocket1.socket().bind(new InetSocketAddress("localhost", this.port1));  
            //��ѡ����ע�ᵽͨ���ϣ�����һ��ѡ���     
            //OP_ACCEPT�����׽��ֽ��ܲ����Ĳ�����λ     
            this.serversocket1.register(this.selector, SelectionKey.OP_ACCEPT);  
  
            //Ȼ���ʼ���ڶ��������     
            this.serversocket2 = ServerSocketChannel.open();  
            this.serversocket2.configureBlocking(false);  
            this.serversocket2.socket().bind(new InetSocketAddress("localhost", this.port2));  
            this.serversocket2.register(this.selector, SelectionKey.OP_ACCEPT);  
  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
  
    }  
  
    /**   
     * �������������   
     * �ͻ������ӷ�����   
     * @throws IOException    
     * */  
    public void accept(SelectionKey key) throws IOException {  
        ServerSocketChannel server = (ServerSocketChannel) key.channel();  
        if (server.equals(serversocket1)) {  
            clientchannel1 = server.accept();  
            clientchannel1.configureBlocking(false);  
            //OP_READ���ڶ�ȡ�����Ĳ�����λ     
            clientchannel1.register(this.selector, SelectionKey.OP_READ);  
        } else {  
            clientchannel2 = server.accept();  
            clientchannel2.configureBlocking(false);  
            //OP_READ���ڶ�ȡ�����Ĳ�����λ     
            clientchannel2.register(this.selector, SelectionKey.OP_READ);  
        }  
    }  
  
    /**   
     * ��ͨ���ж�ȡ����   
     * �����ж��Ǹ��Ǹ�����ͨ����   
     * @throws IOException    
     * */  
    public void read(SelectionKey key) throws IOException {  
  
        this.buf.clear();  
        //ͨ��ѡ������ҵ�֮ǰע���ͨ��     
        //��������ע�����ServerSocketChannelΪʲô�᷵��һ��SocketChannel����     
        SocketChannel channel = (SocketChannel) key.channel();  
        //��ͨ�������ȡ���ݵ������������ض�ȡ�ֽ���     
        int count = channel.read(this.buf);  
  
        if (count == -1) {  
            //ȡ�����ͨ����ע��     
            key.channel().close();  
            key.cancel();  
            return;  
        }  
  
        //�����ݴӻ��������ó���     
        String input = new String(this.buf.array()).trim();  
        //��ô�����ж������ӵ����ַ���     
        if (channel.equals(this.clientchannel1)) {  
            System.out.println("��ӭ��ʹ�÷���A");  
            System.out.println("��������Ϊ��" + input);  
        } else {  
            System.out.println("��ӭ��ʹ�÷���B");  
            System.out.println("��������Ϊ��" + input);  
        }  
  
    }  
  
    @Override  
    public void run() {  
        while (true) {  
            try {  
                System.out.println("running ... ");  
                //ѡ��һ���������Ӧ��ͨ����Ϊ I/O ����׼��������     
                this.selector.select();  
  
                //���ش�ѡ��������ѡ�����     
                //public abstract Set<SelectionKey> selectedKeys()     
                Iterator selectorKeys = this.selector.selectedKeys().iterator();  
                while (selectorKeys.hasNext()) {  
                    System.out.println("running2 ... ");  
                    //�����ҵ���ǰ��ѡ���     
                    SelectionKey key = (SelectionKey) selectorKeys.next();  
                    //Ȼ�����ӷ��ؼ�������ɾ��     
                    selectorKeys.remove();  
                    if (!key.isValid()) { // ѡ�����Ч  
                        continue;  
                    }  
                    if (key.isAcceptable()) {  
                        //�������������ô����Ӧ     
                        this.accept(key);  
                    } else if (key.isReadable()) {  
                        //��ȡ�ͻ��˵�����     
                        this.read(key);
                    }  
                }  
            } catch (Exception e) {  
                e.printStackTrace();  
            }  
        }  
    }  
  
    public static void main(String[] args) {
        IntBuffer intBuffer = IntBuffer.allocate(5);
        int size=5;
        for (int i = 0; i < size; i++) {
            intBuffer.put((i+(Integer.MAX_VALUE-300)));
        }
        System.out.println("intBuffer.position()>>>"+intBuffer.position());
        intBuffer.flip();
        System.out.println("intBuffer.position()>>>"+intBuffer.position());
        
        while(intBuffer.hasRemaining()){
            System.out.println("while.position()>>>"+intBuffer.position());
            System.out.println(intBuffer.get());
        }
        Runnable runable = new Runnable() {
            
            @Override
            public void run() {
                // TODO Auto-generated method stub
                
            }
        };
        Callable<Integer> callble = new Callable<Integer>() {

            @Override
            public Integer call() throws Exception {
                // TODO Auto-generated method stub
                return null;
            }
        };
//        System.out.println(intBuffer);
    }  
}  