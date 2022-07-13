package chatTask.client;

import java.io.IOException;
import java.net.Socket;

import chatTask.Connection;
import chatTask.ConsoleHelper;
import chatTask.Message;
import chatTask.MessageType;

/*Клиент сначала спрашивает у пользователя адрес и порт сервера
 * присоединяется, отправляет имя пользователя серверу - ждем принятия этой информации  сервером
 * */
public class Client {
	protected Connection connection;
	private volatile boolean clientConnected = false;
	
	public static void main(String[] args) {
		Client cl = new Client();
		cl.run();
	}
	

	public void run() {
		SocketThread st = getSocketThread();//создание вспомогательного потока
		st.setDaemon(true);//поменили как демон, чтобы при выходе из программы вспомогательны йпоток прерыывлся автоматически
		st.start();
		try {
			synchronized(this) {//блокировка на объекте - метод вейт в блоке синхронизации
				wait();}// поток приходит сюда и разблокирует монитор этого объекта, для продолжения этого потока экземпляр ждем нотифая - поток в состоянии WAIT
			//когда пройдет NOTIFY на этом объекте - то поток продолжит выполняться
			
		} catch (InterruptedException e) {
			ConsoleHelper.writeMessage("Произошла ошибка во время выполнения");
			return;
		}
		if(clientConnected) {
			ConsoleHelper.writeMessage("Соединение установлено.\r\n"
					+ "Для выхода наберите команду 'exit'.");
		}
		else {
			ConsoleHelper.writeMessage("Произошла ошибка во время выполнения");
		}
		
		while(clientConnected) {
			String str = ConsoleHelper.readString();
			if(str.equalsIgnoreCase("exit")) {
				System.out.println("выход");
				break;}
			
			if(shouldSendTextFromConsole()) {
					sendTextMessage(str);
			}
		}		
	}
	
	
	
	protected String getServerAddress() {
		ConsoleHelper.writeMessage("Введите адрес сервера");
		String address = ConsoleHelper.readString();
		return address;
	}
	
	protected int getServerPort() {
		ConsoleHelper.writeMessage("Введите порт");
		int port = ConsoleHelper.readInt();
		return port;
	}
	
	protected String getUserName() {
		ConsoleHelper.writeMessage("Введите имя");
		return ConsoleHelper.readString();
	}
	protected boolean shouldSendTextFromConsole() {
		return true;
	}
	
	protected SocketThread getSocketThread() {
		return new SocketThread();
	}
	
    protected void sendTextMessage(String text) {
        try {
            connection.send(new Message(MessageType.TEXT, text));
        } catch (IOException e) {
            ConsoleHelper.writeMessage("Не удалось отправить сообщение");
            clientConnected = false;
        }
    }
	
	
	
	
	
	
	
	public class SocketThread extends Thread{
		
		@Override
		public void run() {
			String address = getServerAddress();
			int port = getServerPort();
			
			try {
				Socket socket = new Socket(address, port);
				connection = new Connection(socket);
				//System.out.println(connection);
				clientHandshake();
				clientMainLoop();
				
				
				
			} catch (IOException|ClassNotFoundException e) {

				notifyConnectionStatusChanged(false);
			} 
			
		}
		
		
		//отвечает за сокетное соединение и читает сообщение
		protected void processIncomingMessage(String message) {
			ConsoleHelper.writeMessage(message);
		}
		
		protected void informAboutAddingNewUser(String userName) {
			ConsoleHelper.writeMessage(userName+": присоединился к чату");
		}
		
		protected void informAboutDeletingNewUser(String userName) {
			ConsoleHelper.writeMessage(userName+": покинул чат");
		}
		protected void notifyConnectionStatusChanged (boolean clientConnected) {
			Client.this.clientConnected = clientConnected;
			synchronized(Client.this) {
				Client.this.notify();
			}
		}
		protected void clientHandshake() throws ClassNotFoundException, IOException {//представление клиента серверу
			while(true) {
				Message message = connection.receive();//получили сообщение
				if(message.getType()==MessageType.NAME_REQUEST){
					String userName = getUserName();
					connection.send(new Message(MessageType.USER_NAME, userName));//если клиент получил сообщение с запросом имени - запрашиваем имя и отправляем на серв
				}
				else if(message.getType()==MessageType.NAME_ACCEPTED) {//сервер принял имя - сообщаем об этом главному потоку
					notifyConnectionStatusChanged(true);//перевод статус поля клиент коннектед в тру и возвращает управления нити (Client.this)
					return;
					
				}
				else {
					throw new IOException("Unexpevted MessageType");
				}
				
			}
		}
		
		protected void clientMainLoop() throws ClassNotFoundException, IOException {

			while(true) {
				Message message = connection.receive();//получение сообщение от сервера
				if(message.getType()==MessageType.TEXT) {
					processIncomingMessage(message.getData());
				}
				else if(message.getType()==MessageType.USER_ADDED) {
					informAboutAddingNewUser(message.getData());
				}
				else if(message.getType()==MessageType.USER_REMOVED) {
					informAboutDeletingNewUser(message.getData());
				}
				else {
					
					throw new IOException("Unexpected message type");
					
				}
			}
		}
		
		
		
	}

}
