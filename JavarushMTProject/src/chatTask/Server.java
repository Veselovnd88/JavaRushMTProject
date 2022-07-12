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
	public static void sendBroadcastMessage(Message message) {
		
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
		/* Протокол общения с клиентом
		 * этап 1  - рукопожатие -занкомтсво с сервером*/
		private Socket socket;
		
		public Handler(Socket socket) {
			this.socket = socket;
		}
		
		private String serverHandshake(Connection connection) throws IOException, ClassNotFoundException {
				while(true) {
					connection.send(new Message(MessageType.NAME_REQUEST));//при присоединении сонекшн шлет запрос имени
					Message message = connection.receive();//получаем
					if(message.getType()!=MessageType.USER_NAME) {
						ConsoleHelper.writeMessage("Получено сообщение не содержащее имя");
						continue;
					}
					String userName = message.getData();
					if(userName.isEmpty()) {
						ConsoleHelper.writeMessage("Имя не может быть пустым");
						continue;
					}
					if(Server.connectionMap.containsKey(userName)) {
						ConsoleHelper.writeMessage("Имя уже существует");
						continue;
					}
					Server.connectionMap.put(userName, connection);
					connection.send(new Message(MessageType.NAME_ACCEPTED));
					return userName;
				}
		}
		
		private void serverMainLoop(Connection connection, String userName) throws ClassNotFoundException, IOException {
			while(true) {
			Message message = connection.receive();
			if(message.getType()==MessageType.TEXT) {
				String str = userName+": "+message.getData();
				Message sendMessage = new Message(MessageType.TEXT,str);
				sendBroadcastMessage(sendMessage);
			}
			else {
				ConsoleHelper.writeMessage("Не удалось отправить сообщение");
			}
			}
		}
		
		private void notifyUsers(Connection connection, String userName) throws IOException {
			for (Map.Entry<String, Connection> entry :connectionMap.entrySet()){
				if(!entry.getKey().equals(userName)) {
				Message message = new Message(MessageType.USER_ADDED, entry.getKey());
				connection.send(message);}
				
			}
		}
	
	}
}
