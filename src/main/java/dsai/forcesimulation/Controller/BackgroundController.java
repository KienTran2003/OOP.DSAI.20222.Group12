package dsai.forcesimulation.Controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

public class BackgroundController implements Initializable {
    private int WEIGHT_SCENE = 1500;
    private ImageView[] img;        //Array contains images of background
    private double velo;            //Velocity of background
    @FXML
    ImageView img1, img2;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        img = new ImageView[] {img1, img2};
    }

    //Move background
    public void move(double velocity){
        if (velocity>0){
            velo = 0.2;
        } else if (velocity < 0) {
            velo = -0.2;
        } else {
            velo = 0;
        }
        //Check if image is exceed weight of the scene then put them back.
        for (ImageView image: img){
            if (image.getLayoutX()-velo < -WEIGHT_SCENE){
                image.setLayoutX(WEIGHT_SCENE);
            } else if (image.getLayoutX()-velo > WEIGHT_SCENE) {
                image.setLayoutX(-WEIGHT_SCENE);
            } else {
                image.setLayoutX(image.getLayoutX()-velo);
            }

        }
    }
}
