package dsai.forcesimulation.Controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;

import java.net.URL;
import java.util.ResourceBundle;

public class CheckboxController {

    @FXML
    private CheckBox forceBox, sumBox, valueBox, massBox, accelerateBox, veloBox, posiBox;

    public boolean getForceBox(){
        return forceBox.isSelected();
    }
    public boolean getSumBox(){
        return sumBox.isSelected();
    }
    public boolean getValueBox(){
        return valueBox.isSelected();
    }
    public boolean getMassBox(){
        return massBox.isSelected();
    }
    public boolean getAccelerateBox(){
        return accelerateBox.isSelected();
    }
    public boolean getVeloBox(){
        return veloBox.isSelected();
    }
    public boolean getPosiBox(){
        return posiBox.isSelected();
    }


}
