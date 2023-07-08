package dsai.forcesimulation.Model.Object;

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
    void setOmega(double omega);public
    DoubleProperty radiusProperty();
    double getRadius();
    void setRadius(double radius) throws Exception;
    void updateAngularPosition(double deltaTime);
    void updateAngularVelocity(double deltaTime);
}
