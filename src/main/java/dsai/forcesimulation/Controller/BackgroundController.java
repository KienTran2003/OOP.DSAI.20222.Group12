package dsai.forcesimulation.Controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

public class BackgroundController implements Initializable {
    @FXML
    ImageView img1, img2;
    private ImageView[] img;
    private double velo;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        img = new ImageView[] {img1, img2};
    }
    public void move(double velocity){
        if (velocity>0){
            velo = 0.2;
        } else if (velocity < 0) {
            velo = -0.2;
        } else {
            velo = 0;
        }

        for (ImageView image: img){
            if (image.getLayoutX()-velo < -1500){
                image.setLayoutX(1500);
            } else if (image.getLayoutX()-velo > 1500) {
                image.setLayoutX(-1500);
            } else {
                image.setLayoutX(image.getLayoutX()-velo);
            }

        }
    }
}
