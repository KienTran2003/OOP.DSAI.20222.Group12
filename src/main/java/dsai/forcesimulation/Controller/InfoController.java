package dsai.forcesimulation.Controller;

import dsai.forcesimulation.Model.Object.MainObject;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import dsai.forcesimulation.Model.Object.Cylinder;

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
		
		if (bool) {
			accelerate.setText(String.format("%.2f",mainObject.getAcceleration()) + " m/s^2");
		if (mainObject instanceof Cylinder) {
			anAccelerate.setText(String.format("%.2f",((Cylinder) mainObject).getGamma()) + " */s^2");
		} else {
			anAccelerate.setText(0 + " */s^2");
			}
	
		} 
		accelerate.setVisible(bool);
	    anAccelerate.setVisible(bool);
	    accLabel.setVisible(bool);
	    anAccLabel.setVisible(bool);
	}

	
}
