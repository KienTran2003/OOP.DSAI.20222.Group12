package dsai.forcesimulation.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

public class ForceController {
	double origin;
    @FXML
    ImageView posiAppForce, negaAppForce, posiFricForce, negaFricForce; 
    @FXML
    private Label appForceLabel, fricLabel, sumLabel;
}
