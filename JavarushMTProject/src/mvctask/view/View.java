package mvctask.view;

import mvctask.controller.Controller;
import mvctask.model.ModelData;

public interface View {
	
	
	void refresh(ModelData modelData);
	void setController(Controller contoller);
	

}
