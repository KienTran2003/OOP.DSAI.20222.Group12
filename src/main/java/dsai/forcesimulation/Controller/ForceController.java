package dsai.forcesimulation.Controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

public class ForceController implements Initializable{
	
	double origin;
    @FXML
    ImageView posiAppForce, negaAppForce, posiFricForce, negaFricForce; 
    @FXML
    private Label appForceLabel, fricLabel;
    
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        origin = negaAppForce.getLayoutX();
    }
    
    public void updateAppForceVector(double applyForce, boolean isShow, boolean isShowValue){
        appForceLabel.setText(String.format("%.0f", applyForce) + "N");
        appForceLabel.setVisible(isShow && isShowValue);
        if (applyForce > 0){
            posiAppForce.setFitWidth(applyForce/2);
            appForceLabel.setLayoutX(posiAppForce.getLayoutX() + posiAppForce.getFitWidth()/2);

            posiAppForce.setVisible(isShow);
            negaAppForce.setVisible(false);

        } else if (applyForce < 0){
            negaAppForce.setLayoutX(origin + applyForce/2);
            negaAppForce.setFitWidth(-applyForce/2);

            appForceLabel.setLayoutX(negaAppForce.getLayoutX() + negaAppForce.getFitWidth()/2);
            posiAppForce.setVisible(false);
            negaAppForce.setVisible(isShow);

        } else {
            appForceLabel.setVisible(false);
            posiAppForce.setVisible(false);
            negaAppForce.setVisible(false);
        }

    }
    
    
}
