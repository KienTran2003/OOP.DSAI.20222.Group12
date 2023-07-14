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
		/*
		 * take a boolean value as an argument. 
		 * If the argument is true, 
		 * the methods set the text of the acceleration labels and display information. 
		 * If the mainObject is an instance of the Cylinder class, 
		 * additional information is displayed. 
		 * 
		 */
		accelerate.setText(String.format("%.2f",mainObject.getAcceleration()) + " m/s^2");
		if (mainObject instanceof Cylinder){
			anAccelerate.setText(String.format("%.2f",((Cylinder) mainObject).getGamma()) + " */s^2");
		} else {
			anAccelerate.setText(0 + " */s^2");
		}
		
		accelerate.setVisible(bool);
		anAccelerate.setVisible(bool);
		accLabel.setVisible(bool);
		anAccLabel.setVisible(bool);
	}
	
	public void showVelo(boolean bool) {
		/*
		 * take a boolean value as an argument. 
		 * If the argument is true, 
		 * the methods set the text of the velocity labels and display information. 
		 * If the mainObject is an instance of the Cylinder class, 
		 * additional information is displayed. 
		 * 
		 */
		velo.setText(String.format("%.2f",mainObject.getVelocity()) + " m/s");
		if (mainObject instanceof Cylinder){
			anVelo.setText(String.format("%.2f",((Cylinder) mainObject).getOmega()) + " */s");
		} else {
			anVelo.setText(0 + " */s");
		}
		
		velo.setVisible(bool);
		anVelo.setVisible(bool);
		veloLabel.setVisible(bool);
		anVeloLabel.setVisible(bool);
    }
	
	
	public void showPosi(boolean bool) {
		/*
		 * take a boolean value as an argument. 
		 * If the argument is true, 
		 * the methods set the text of the position labels and display information. 
		 * If the mainObject is an instance of the Cylinder class, 
		 * additional information is displayed. 
		 * 
		 */
		posi.setText(String.format("%.2f",mainObject.getPosition()) + " m");
        if (mainObject instanceof Cylinder){
            anPosi.setText(String.format("%.2f", ((Cylinder) mainObject).getTheta()) + " *");
        } else {
            anPosi.setText(0 + " *");
        }
        
        posi.setVisible(bool);
        anPosi.setVisible(bool);
        posiLabel.setVisible(bool);
        anPosiLabel.setVisible(bool);
    }

	
}
