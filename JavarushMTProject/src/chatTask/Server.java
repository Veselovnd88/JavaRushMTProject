package chatTask;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/*Сервер поддерживает много соединений с разными клиентами
 * Сервер создает сокетное соединение -> с цикле ждет пока кто нибудь не подключится, создает поток обработчик - Handler
 * в котором будет происходить обмен сообщениями, ожидает следующее подключение
 * */
public class Server {
	
	private static Map<String, Connection> connectionMap = new ConcurrentHashMap<>();//мапа всех соединений, ключ - имя клиента, значение - объект коннекшн, потокобезопасный

	public static void main(String[] args) {
		ConsoleHelper.writeMessage("Введите номер порта для создания сокета");
		int port = ConsoleHelper.readInt();
		ServerSocket ss = null;
		try {
			ss = new ServerSocket(port);//создание серверного сокета на порте
			ConsoleHelper.writeMessage("Сервер запущен");
			while(true) {
				Socket income = ss.accept();//сокет слушает пока не придет входящее соединение
				Handler handler = new Handler(income);// когда пришло создается хэндлер и запускается
				handler.start();//создали и запустили нить
			}
			
			
			
		} catch (IOException e) {
			try {
				ss.close();
			} catch (IOException e1) {
				ConsoleHelper.writeMessage("Ошибка закрытия сокета");
				e1.printStackTrace();
			}
		}
		
	}
	public void sendBroadcastMessage(Message message) {
		
		connectionMap.forEach((x,y)-> {
			try {
				y.send(message);
			} catch (IOException e) {
				ConsoleHelper.writeMessage("Не удалось отправить сообщение");
				e.printStackTrace();
			}
		});
	}
	
	
	
	private static class Handler extends Thread{//пприватный статический класс
		private Socket socket;
		
		public Handler(Socket socket) {
			this.socket = socket;
		}
		
		
		
		
	}

}
