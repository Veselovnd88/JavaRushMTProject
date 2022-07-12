package chatTask.client;

import java.io.IOException;

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
				wait();}
			
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
	}

}
