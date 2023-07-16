package dsai.forcesimulation.Controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

public class ForceController implements Initializable{
	
	double origin; // original layout x-coordinate of the negaAppForce ImageView
    @FXML
    ImageView posiAppForce, negaAppForce, posiFricForce, negaFricForce, posiSumForce, negaSumForce;
    @FXML
    private Label appForceLabel, fricLabel, sumLabel;
    
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    	/*
    	 * sets the value of the origin field to the layout x-coordinate 
    	 * of the negaAppForce ImageView
    	 * 
    	 */
        origin = negaAppForce.getLayoutX();
    }
    
    public void updateAppForceVector(double applyForce, boolean isShow, boolean isShowValue){
        /*
         * update the display of the applied force vectors. 
         * The first boolean value determines whether the vector is visible.
         * The second boolean value determines whether the corresponding label
         * is visible. 
         * 
         */
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
    
    public void updateFrictionVector(double fricForce, boolean isShow, boolean isShowValue){
    	/*
         * update the display of the Friction force vectors. 
         * The first boolean value determines whether or not the vector is visible. 
         * The second boolean value determines whether or not the corresponding label 
         * is visible. 
         * 
         */
    	fricLabel.setText(String.format("%.0f", fricForce) + "N");
        fricLabel.setVisible(isShow && isShowValue);
        if (fricForce > 0){
            posiFricForce.setFitWidth(fricForce/2);
            fricLabel.setLayoutX(posiFricForce.getLayoutX() + posiFricForce.getFitWidth()/2);
            
            posiFricForce.setVisible(isShow);
            negaFricForce.setVisible(false);
        } else if (fricForce < 0) {
            negaFricForce.setLayoutX(origin + (fricForce)/2);
            negaFricForce.setFitWidth(-fricForce/2);
            fricLabel.setLayoutX(negaFricForce.getLayoutX() + negaFricForce.getFitWidth()/2);
            
            posiFricForce.setVisible(false);
            negaFricForce.setVisible(isShow);
        } else {
            fricLabel.setVisible(false);
            negaFricForce.setVisible(false);
            posiFricForce.setVisible(false);
        }
    }
    
    public void updateSumForce(double applyForce, double fricForce, boolean isShow, boolean isShowValue){
    	/*
         * update the display of the Sum force vectors. 
         * The first boolean value determines whether or not the vector is visible. 
         * The second boolean value determines whether or not the corresponding label 
         * is visible. 
         * 
         */
    	sumLabel.setText(String.format("%.0f", applyForce+fricForce) + "N");
        sumLabel.setVisible(isShow && isShowValue);
        if (applyForce + fricForce > 0){
            posiSumForce.setFitWidth((applyForce + fricForce)/2);
            sumLabel.setLayoutX(posiSumForce.getLayoutX() + posiSumForce.getFitWidth()/2);
            
            posiSumForce.setVisible(isShow);
            negaSumForce.setVisible(false);
        } else if (applyForce+fricForce < 0) {
            negaSumForce.setLayoutX(origin + (applyForce + fricForce)/2);
            negaSumForce.setFitWidth((-applyForce-fricForce)/2);
            sumLabel.setLayoutX(negaSumForce.getLayoutX() + negaSumForce.getFitWidth()/2);
            
            negaSumForce.setVisible(isShow);
            posiSumForce.setVisible(false);
        } else {
            sumLabel.setVisible(false);
            posiSumForce.setVisible(false);
            negaSumForce.setVisible(false);
        }
    }
    
    
    public void updateAllForce(double sliderValue, double friction, boolean isShowForce,boolean isShowSum, boolean isShowValue){
        /*
         * update the display of all three force vectors.
         */
    	this.updateAppForceVector(sliderValue, isShowForce, isShowValue);
        this.updateFrictionVector(friction, isShowForce, isShowValue);
        this.updateSumForce(sliderValue,friction, isShowSum, isShowValue);
    }
    
    
}
