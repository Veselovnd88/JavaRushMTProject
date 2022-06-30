package mvctask;

import mvctask.controller.Controller;
import mvctask.model.MainModel;
import mvctask.model.Model;
import mvctask.view.UsersView;

public class Solution {
    public static void main(String[] args) {
       // Model model = new FakeModel();
        Model model = new MainModel();
        UsersView usersView = new UsersView();
        Controller controller = new Controller();

        usersView.setController(controller);
        controller.setModel(model);
        controller.setUsersView(usersView);

        usersView.fireEventShowAllUsers();
        usersView.fireEventShowDeletedUsers();
    }
}