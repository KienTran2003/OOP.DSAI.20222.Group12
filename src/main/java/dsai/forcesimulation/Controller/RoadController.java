package dsai.forcesimulation.Controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.shape.Rectangle;

import java.net.URL;
import java.util.ResourceBundle;

public class RoadController implements Initializable {
    private Rectangle[] recList ;
    @FXML
    private Rectangle rec1, rec2, rec3, rec4, rec5, rec6, rec7, rec8, rec9, rec10, rec11, rec12;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        recList = new Rectangle[]{rec1, rec2, rec3, rec4, rec5, rec6, rec7, rec8, rec9, rec10, rec11, rec12};
    }
    public void move(double deltaX){
        for (Rectangle rec: recList){
        	int leftLimit = -180; // = - (100 + 40 + 40), 
        							// 100 is the width of a rectangle; 40 is the distance between rectangles
        	int rightLimit = 1500; // the screen is between 0 and 1500
            if (rec.getLayoutX() - deltaX < leftLimit){
                rec.setLayoutX(rightLimit - (deltaX + leftLimit - rec.getLayoutX()));
            } else if (rec.getLayoutX() - deltaX  > 1500){
                rec.setLayoutX(leftLimit - (deltaX - rec.getLayoutX() + rightLimit));
            } else {
                rec.setLayoutX(rec.getLayoutX() - deltaX);
            }
        }
    }
    public void restartRoad(){
        for (int i = 0; i< recList.length; i++){
            recList[i].setLayoutX(i*140 - 140);
        }
    }
}

