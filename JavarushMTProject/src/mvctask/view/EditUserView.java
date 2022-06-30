package mvctask.view;

import mvctask.controller.Controller;
import mvctask.model.ModelData;

//view для отображения данныъ о редактировании юзера
public class EditUserView implements View{
	private Controller controller;
	@Override
	public void refresh(ModelData modelData) {
		System.out.println("User to be edited: ");
		System.out.println("\t"+modelData.getActiveUser());
		System.out.println("===================================================");
		
	}

	@Override
	public void setController(Controller contoller) {
		this.controller=contoller;
		
	}
	

}
