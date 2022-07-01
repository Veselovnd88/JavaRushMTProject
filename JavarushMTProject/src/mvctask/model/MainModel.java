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
		
		modelData.setUsers(getAllUsers());
		modelData.setDisplayDeletedUserList(false);
		
	}
	
	public void loadDeletedUsers() {
		List<User> users = userService.getAllDeletedUsers();
		modelData.setUsers(users);
		modelData.setDisplayDeletedUserList(true);
	}
	
	public void loadUserById(long userId) {
		User user = userService.getUsersById(userId);
		modelData.setActiveUser(user);
		}
	
	public void deleteUserById(long id) {
		userService.deleteUser(id);
		modelData.setUsers(getAllUsers());
	}
	public void changeUserData(String name, long id, int level) {
		userService.createOrUpdateUser(name, id, level);
		modelData.setUsers(getAllUsers());
	}
	
	private List<User> getAllUsers(){
		List<User> allUsers = userService.getUsersBetweenLevels(1,100);//тут работает вся логика получения юзеров+ выборка только активных
		
		return userService.filterOnlyActiveUsers(allUsers);
	}

}
