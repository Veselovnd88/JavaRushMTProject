package mvctask.view;

import mvctask.bean.User;
import mvctask.controller.Controller;
import mvctask.model.ModelData;

public class UsersView implements View {
	private Controller controller;// устанавливается контроллер
	

	@Override
	public void refresh(ModelData modelData) {
		if(!modelData.isDisplayDeletedUserList()) {
		
		System.out.println("All Users:");}
		else {
			System.out.println("All Deleted Users:");
		}
		
		for(User u : modelData.getUsers()) {
			System.out.println("\t"+u);
		}
		
		
		System.out.println("===================================================");
		
		
	}

	@Override
	public void setController(Controller contoller) {
		this.controller = contoller;
		
	}
	public void fireEventShowAllUsers() {
		controller.onShowAllUsers();
	}
	
	public void fireEventShowDeletedUsers() {
		controller.onShowAllDeletedUsers();
		}
	
	public void fireEventOpenUserEditForm(long id) {
		controller.onOpenUserEditForm(id);
		}
	

}
