package mvctask.model;

import java.util.ArrayList;
import java.util.List;

import mvctask.bean.User;

public class ModelData {
	private boolean displayDeletedUserList;
	private User activeUser;
	private List<User> users = new ArrayList<User>();

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public User getActiveUser() {
		return activeUser;
	}

	public void setActiveUser(User activeUser) {
		this.activeUser = activeUser;
	}

	public boolean isDisplayDeletedUserList() {
		return displayDeletedUserList;
	}

	public void setDisplayDeletedUserList(boolean displayDeletedUserList) {
		this.displayDeletedUserList = displayDeletedUserList;
	}

	
	
}
