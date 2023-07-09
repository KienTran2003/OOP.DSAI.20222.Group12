package dsai.forcesimulation.Test;

import javafx.application.Application;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import dsai.forcesimulation.Model.Object.CubeBox;
import dsai.forcesimulation.Model.Surface.Surface;

public class VelocityAnimation extends Application {
    private DoubleProperty appliedForceProperty = new SimpleDoubleProperty(0.0);
    private Label velocityLabel = new Label();
    private Label accelerationLabel = new Label();

    @Override
    public void start(Stage primaryStage) {
        // Tạo đối tượng CubeBox và Surface
        CubeBox cubeBox = new CubeBox(10, 50);
        Surface surface = new Surface(0.5, 0.2);

        // Tạo Slider
        Slider slider = new Slider(-500, 500, 0);
        slider.setMinWidth(200);

        // Liên kết appliedForceProperty với giá trị của Slider
        appliedForceProperty.bind(slider.valueProperty());

        // Truyền appliedForce mới khi giá trị trên Slider thay đổi
        appliedForceProperty.addListener((observable, oldValue, newValue) -> {
            cubeBox.calculateFrictionForces(newValue.doubleValue(), surface);
            cubeBox.updateAttribute(newValue.doubleValue(), surface);
            velocityLabel.setText("Velocity: " + cubeBox.getVelocity());
            accelerationLabel.setText("Acceleration: " + cubeBox.getAcceleration());
        });

        // Tạo giao diện người dùng
        VBox root = new VBox(slider, velocityLabel, accelerationLabel);
        Scene scene = new Scene(root, 400, 200);

        primaryStage.setScene(scene);
        primaryStage.setTitle("CubeBox Velocity Animation");
        primaryStage.show();

        // Tính toán và cập nhật lần đầu
        cubeBox.calculateFrictionForces(appliedForceProperty.get(), surface);
        cubeBox.updateAttribute(appliedForceProperty.get(), surface);
        velocityLabel.setText("Velocity: " + cubeBox.getVelocity());
        accelerationLabel.setText("Acceleration: " + cubeBox.getAcceleration());
    }

    public static void main(String[] args) {
        launch(args);
    }
}
