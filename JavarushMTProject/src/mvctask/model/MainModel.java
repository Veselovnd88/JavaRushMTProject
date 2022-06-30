package mvctask.model;

import java.util.List;

import mvctask.bean.User;
import mvctask.model.service.UserService;
import mvctask.model.service.UserServiceImpl;

public class MainModel implements Model {
	private ModelData modelData = new ModelData();
	
	UserService userService = new UserServiceImpl();

	@Override
	public ModelData getModelData() {
		
		return modelData;
	}

	@Override
	public void loadUsers() {
		List<User> result = userService.getUsersBetweenLevels(1, 100);
		modelData.setUsers(result);
		
	}
	
	public void loadDeletedUsers() {
		List<User> users = userService.getAllDeletedUsers();
		modelData.setUsers(users);
	}

}
