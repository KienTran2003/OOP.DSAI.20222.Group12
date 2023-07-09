package dsai.forcesimulation.Model.Object;

import dsai.forcesimulation.Model.Surface.Surface;
import javafx.beans.property.DoubleProperty;

public interface RotatingObject {
    DoubleProperty gammaProperty();
    double getGamma();
    void setGamma(double gamma);
    double getTheta();
    DoubleProperty thetaProperty();
    void setTheta(double theta);
    double getOmega();
    DoubleProperty omegaProperty();
    void setOmega(double omega);
    DoubleProperty radiusProperty();
    double getRadius();
    void setRadius(double radius) throws Exception;
    void updateAngularPosition(); //cal theta
    void updateAngularVelocity(); //cal omega
    double calculateAngularAcceleration(double appliedForce, Surface surface); //cal gamma
}