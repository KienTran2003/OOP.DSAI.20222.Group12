
/* This class is controller of slider which can change applied force */
package dsai.forcesimulation.Controller;

import dsai.forcesimulation.Model.Object.Cylinder;
import dsai.forcesimulation.Model.Object.MainObject;
import dsai.forcesimulation.Model.Surface.Surface;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.util.Duration;
import java.net.URL;
import java.util.ResourceBundle;

public class SliderController implements Initializable {

    private final int MAX_SPEED = 50;    //Max velocity that object can reach
    private MainObject mainObject;         //Object that is running in the road
    private ForceController forceController;    //Controller of vectors of force
    private CheckboxController checkboxController;      //Controller of check box
    private Surface surface;            //Surface to get friction coefficient

    private Timeline sliderTimeline;             //Timeline define animations of javafx application



    @FXML
    Slider slider;            //slider to control applied force
    @FXML
    Label forceLabel;         //Label to show applied force


    public SliderController(MainObject mainObject,Surface surface, ForceController forceController, CheckboxController checkboxController){
        this.mainObject =  mainObject;
        this.surface = surface;
        this.forceController = forceController;
        this.checkboxController = checkboxController;
    }

    public void setMainObject(MainObject mainObject) {
        this.mainObject = mainObject;
    }

    public MainObject getMainObject() {
        return mainObject;
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        slider.setOnMouseDragged(e -> {                 //Set action when mouse drag in slider

            if (mainObject.getVelocity() > 50 && slider.getValue() > 0){        //Set applied force = 0 when velocity of object exceed max velocity
                this.slider.setValue(0);

            } else if (mainObject.getVelocity() < -50 && slider.getValue() < 0) {
                this.slider.setValue(0);
            } else {
                if (sliderTimeline != null){sliderTimeline.stop();}
                Duration newDuration = Duration.millis(10);
                KeyFrame newKeyFrame = new KeyFrame(newDuration, event -> {  //Defines target values at a specified point in
                    update();                                                // time for a set of variables that are interpolated along a Timeline.
                });

                forceController.updateAllForce(slider.getValue(), mainObject.calculateFriction(slider.getValue(), surface.getStaticCoefficient(), surface.getKineticCoefficient()), checkboxController.getForceBox(),checkboxController.getSumBox(), checkboxController.getValueBox());
                sliderTimeline = new Timeline(newKeyFrame);
                sliderTimeline.setCycleCount(Animation.INDEFINITE);
                sliderTimeline.play();
                String formattedValue = String.format("%.0f", slider.getValue());
                forceLabel.setText(formattedValue + " newtons");        //Label show magnitude of applied force
            }


        });
        slider.setOnMouseReleased(e -> {                //Set action when mouse is released from slider
            slider.setValue(0);
            String formattedValue = String.format("%.0f", slider.getValue());
            forceLabel.setText(formattedValue + " newtons");

            forceController.updateAllForce(slider.getValue(), mainObject.calculateFriction(slider.getValue(), surface.getStaticCoefficient(), surface.getKineticCoefficient()), checkboxController.getForceBox(),checkboxController.getSumBox(), checkboxController.getValueBox());

            if (sliderTimeline != null){sliderTimeline.stop();}
            Duration newDuration = Duration.millis(10);
            KeyFrame newKeyFrame = new KeyFrame(newDuration, event -> {             // Create keyframe to update force continuously when slider does not change value
                update();
            });
            sliderTimeline = new Timeline(newKeyFrame);
            sliderTimeline.setCycleCount(Animation.INDEFINITE);
            sliderTimeline.play();
        });
    }

    void update(){                                  //Update attribute of object: position, acceleration, velocity,...
        double appliedForce = slider.getValue();    //Update applied force and friction
        double friction = mainObject.calculateFriction(slider.getValue(), surface.getStaticCoefficient(), surface.getKineticCoefficient());
        double acceleration = mainObject.calculateAcceleration(appliedForce, friction);

        //Update force
        forceController.updateAllForce(slider.getValue(),friction , checkboxController.getForceBox(),checkboxController.getSumBox(), checkboxController.getValueBox());

        //Update attribute of object
        if (mainObject instanceof Cylinder){
            double gamma = ((Cylinder) mainObject).calculateGamma(friction, mainObject.getMass(), mainObject.getSide());
            ((Cylinder) mainObject).updateAttribute(acceleration, gamma);
        } else {
            mainObject.updateAttribute(acceleration);
        }
    }

    void setDisableSlider(boolean bool){
        this.slider.setDisable(bool);
    }
    //Limit velocity of object
    void updateLimitVelo(){
        if (mainObject.getVelocity() > MAX_SPEED && slider.getValue() > 0){
            this.slider.setValue(0);
        } else if (mainObject.getVelocity() < -MAX_SPEED && slider.getValue() < 0) {
            this.slider.setValue(0);
        }
    }
}


