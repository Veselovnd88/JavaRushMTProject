package chatTask.client;

public class ClientGuiController extends Client {
	
	ClientGuiModel model = new ClientGuiModel();
	ClientGuiView view = new ClientGuiView(this);
	
	public static void main(String[] args) {
		ClientGuiController cgc = new ClientGuiController();
		cgc.run();
	}
	@Override
	public void run() {
		getSocketThread().run();
	}

	@Override
	protected String getServerAddress() {
		return view.getServerAddress();
	}

	@Override
	protected SocketThread getSocketThread() {
		return new GuiSocketThread();
	}
	
	@Override
	protected int getServerPort() {
		return view.getServerPort();
	}

	@Override
	protected String getUserName() {
		return view.getUserName();
	}





	public class GuiSocketThread extends SocketThread{
		@Override
		protected void processIncomingMessage(String message) {
			model.setNewMessage(message);
			view.refreshMessages();
		}

		@Override
		protected void informAboutAddingNewUser(String userName) {
			model.addUser(userName);
			view.refreshUsers();
		}

		@Override
		protected void informAboutDeletingNewUser(String userName) {
			model.deleteUser(userName);
			view.refreshUsers();
		}

		@Override
		protected void notifyConnectionStatusChanged(boolean clientConnected) {
			view.notifyConnectionStatusChanged(clientConnected);
		}
		
		
		
	}
	public ClientGuiModel getModel() {
		return this.model;
	}

}
