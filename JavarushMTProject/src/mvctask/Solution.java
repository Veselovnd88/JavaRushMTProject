package mvctask;

import mvctask.controller.Controller;
import mvctask.model.MainModel;
import mvctask.model.Model;
import mvctask.view.EditUserView;
import mvctask.view.UsersView;

public class Solution {
    public static void main(String[] args) {
       // Model model = new FakeModel();
        Model model = new MainModel();
        UsersView usersView = new UsersView();
        EditUserView editUserView = new EditUserView();
        Controller controller = new Controller();
        editUserView.setController(controller);
        usersView.setController(controller);
        controller.setModel(model);
        controller.setUsersView(usersView);
        controller.setEditUserView(editUserView);

        usersView.fireEventShowAllUsers();
        usersView.fireEventOpenUserEditForm(126);
        editUserView.fireEventUserDeleted(124L);
        editUserView.fireEventUserChanged("Vasya",125,2);
        usersView.fireEventShowDeletedUsers();
        usersView.fireEventShowAllUsers();
    }
}