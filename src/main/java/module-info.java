module dsai.forcesimulation {
    requires javafx.controls;
    requires javafx.fxml;


    exports dsai.forcesimulation.Model.Object;
    opens dsai.forcesimulation.Model.Object to javafx.fxml;
    exports dsai.forcesimulation.Controller;
    opens dsai.forcesimulation.Controller to javafx.fxml;
    exports dsai.forcesimulation;
    opens dsai.forcesimulation to javafx.fxml;
    exports dsai.forcesimulation.Model.Surface;
    opens dsai.forcesimulation.Model.Surface to javafx.fxml;
}