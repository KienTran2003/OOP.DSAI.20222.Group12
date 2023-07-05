module dsai.forcesimulation {
    requires javafx.controls;
    requires javafx.fxml;
            
                            
    opens dsai.forcesimulation to javafx.fxml;
    exports dsai.forcesimulation;
}