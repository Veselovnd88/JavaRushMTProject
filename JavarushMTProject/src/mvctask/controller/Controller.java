package mvctask.controller;

import mvctask.model.Model;
import mvctask.view.EditUserView;
import mvctask.view.UsersView;

public class Controller {//получает запрос от клиента, оповещает модель, модель обновляет МОдель Дата
	//контроллер не содержит никакой логики, только управляет
	private Model model;
	private UsersView usersView;
	private EditUserView editUserView;


	public void setEditUserView(EditUserView editUserView) {
		this.editUserView = editUserView;
	}

	public void setUsersView(UsersView usersView) {
		this.usersView = usersView;
	}

	public void setModel(Model model) {
		this.model = model;
	}
	
	public void onShowAllUsers() {
		model.loadUsers();
		usersView.refresh(model.getModelData());
	}
	
	public void onShowAllDeletedUsers() {
		model.loadDeletedUsers();
		usersView.refresh(model.getModelData());
	}

}
