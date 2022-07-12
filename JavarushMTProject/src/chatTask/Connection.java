package chatTask;
/*Этот класс обертка над классом net.Socket
 * будет сериализовать и десериализовать данные - объекты Message в сокет
 * методы могут вызываться из разныъ потоков*/

import java.io.Closeable;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketAddress;


public class Connection implements Closeable {
	private final Socket socket;
	private final ObjectOutputStream out;
	private final ObjectInputStream in;

	
	public Connection(Socket socket) throws IOException {
		this.socket = socket;
		this.out = new ObjectOutputStream(socket.getOutputStream());//создали потоки  - исх
		this.in = new ObjectInputStream(socket.getInputStream());//поток вх.
	}
	
	public void send (Message message) throws IOException {
		synchronized(out) {//синхронизация на объекте out
			out.writeObject(message);}
		
	}
	
	public Message receive() throws ClassNotFoundException, IOException {
		synchronized(in){//синхронизация на объекте in
			Message message = (Message) in.readObject();//десериализация - кастуем к нашему объекту
			return message;
		}
	}
	
	public SocketAddress getRemoteSocketAddress() {
		return socket.getRemoteSocketAddress();
	}
	
	
	public void close() throws IOException {
		socket.close();
		out.close();
		in.close();
	}
	
	
	
	
	
	
	
	
	
	

}
