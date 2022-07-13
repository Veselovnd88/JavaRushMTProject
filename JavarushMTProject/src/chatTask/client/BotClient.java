package chatTask.client;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import chatTask.ConsoleHelper;

public class BotClient extends Client {
	
	
	
	public static void main(String[] args) {
		BotClient bc = new BotClient();
		bc.run();
	}

	@Override
	protected String getUserName() {
		return "date_bot_"+(int) (Math.random()*100);
	}

	@Override
	protected boolean shouldSendTextFromConsole() {
		return false;
	}

	@Override
	protected SocketThread getSocketThread() {	
		return new BotSocketThread();
	}

	public class BotSocketThread extends SocketThread{
		@Override
		protected void processIncomingMessage(String message) {
			ConsoleHelper.writeMessage(message);
			String[] parts = message.split(": ");
			if(parts.length<2) {
				return;
			}
			String name = parts[0].trim();
			String mess = parts[1].trim();
			String answer ="";
			Calendar calendar = Calendar.getInstance();

			switch(mess){
			case("дата"):
				answer = new SimpleDateFormat("d.MM.YYYY").format(Calendar.getInstance().getTime());
				break;
			case("день"):
				answer = new SimpleDateFormat("d").format(Calendar.getInstance().getTime());
				break;
			case("месяц"):
				answer = new SimpleDateFormat("MMMM").format(Calendar.getInstance().getTime());
				break;
			case("год"):
				answer = new SimpleDateFormat("YYYY").format(Calendar.getInstance().getTime());
				break;
			case("время"):
				answer = new SimpleDateFormat("H:mm:ss").format(Calendar.getInstance().getTime());
				break;
			case("час"):
				answer = new SimpleDateFormat("H").format(Calendar.getInstance().getTime());
				break;
			case("минуты"):
				answer = new SimpleDateFormat("m").format(Calendar.getInstance().getTime());
				break;
			case("секунды"):{
				answer = new SimpleDateFormat("s").format(Calendar.getInstance().getTime());
				break;
			}
			}
				if(!answer.equals("")) {
					sendTextMessage("Информация для "+name+": "+answer);}
		}

		@Override
		protected void clientHandshake() throws ClassNotFoundException, IOException {
			super.clientHandshake();
		}

		@Override
		protected void clientMainLoop() throws ClassNotFoundException, IOException {
			sendTextMessage("Привет чатику. Я бот. Понимаю команды: дата, день, месяц, год, время, час, минуты, секунды.");
			super.clientMainLoop();
		}
	}
}
