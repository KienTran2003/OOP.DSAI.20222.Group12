package dsai.forcesimulation.Controller;

import dsai.forcesimulation.Model.Object.MainObject;
import dsai.forcesimulation.Model.Surface.Surface;

import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;

import java.net.URL;
import java.util.ResourceBundle;

public class SliderController implements Initializable {

    private final int MAX_SPEED = 50;
    private MainObject mainObject;
    private ForceController forceController;
    private CheckboxController checkboxController;
    private Surface surface;

    private Timeline sliderTimeline;



    @FXML
    Slider slider;
    @FXML
    Label forceLabel;


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


        slider.setOnMouseDragged(e -> {


        });
        slider.setOnMouseReleased(e -> {

        });
    }

    void update(){

    }

    void setDisableSlider(boolean bool){
        this.slider.setDisable(bool);
    }
    void updateLimitVelo(){
        if (mainObject.getVelocity() > MAX_SPEED && slider.getValue() > 0){
            this.slider.setValue(0);
        } else if (mainObject.getVelocity() < -MAX_SPEED && slider.getValue() < 0) {
            this.slider.setValue(0);
        }
    }
}


