package dsai.forcesimulation.Model.Object;

import dsai.forcesimulation.Model.Surface.Surface;
import javafx.beans.property.DoubleProperty;

public interface RotatingObject {
    public double calculateGamma(double friction, double mass, double radius);
}