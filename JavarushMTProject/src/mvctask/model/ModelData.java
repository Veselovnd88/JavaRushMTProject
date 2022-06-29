package mvctask.model;

import java.util.ArrayList;
import java.util.List;

import mvctask.bean.User;

public class ModelData {
	private List<User> users = new ArrayList<User>();

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}
	
	
}
