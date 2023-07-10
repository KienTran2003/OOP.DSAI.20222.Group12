package dsai.forcesimulation.Controller;

import dsai.forcesimulation.Model.Object.MainObject;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class InfoController {
	
	private MainObject mainObject;
	
	@FXML
	private Label accelerate, velo, posi, 
				  anAccelerate, anVelo, anPosi, 
				  accLabel, veloLabel, posiLabel, 
				  anAccLabel, anVeloLabel, anPosiLabel;
	
	
	public InfoController(MainObject mainObject) {
		this.mainObject = mainObject;
	}
	
	public void setMainObject(MainObject mainObject) {
		this.mainObject = mainObject;
	}
	
	public void showAccelerate(boolean bool) {
		
	}
	
}
