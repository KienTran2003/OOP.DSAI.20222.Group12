package dsai.forcesimulation.Model.Object;

import dsai.forcesimulation.Model.Surface.Surface;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

public class Cylinder extends MainObject implements RotatingObject{
    private DoubleProperty radius = new SimpleDoubleProperty();
    private DoubleProperty gamma = new SimpleDoubleProperty(); //angular acceleration
    private DoubleProperty theta = new SimpleDoubleProperty(); //angular position
    private DoubleProperty omega = new SimpleDoubleProperty(); //angular velocity

    public Cylinder(double radius, double mass) {
        super(mass);
        setRadius(radius);
    }
    public double getRadius() {
        return radius.get();
    }

    public DoubleProperty radiusProperty() {
        return radius;
    }
    public void setRadius(double radius){
        this.radius.set(radius);
    }

    public double getGamma() {
        return gamma.get();
    }

    public DoubleProperty gammaProperty() {
        return gamma;
    }

    public void setGamma(double gamma) {
        this.gamma.set(gamma);
    }

    public double getTheta() {
        return theta.get();
    }

    public DoubleProperty thetaProperty() {
        return theta;
    }

    public void setTheta(double theta) {
        this.theta.set(theta);
    }

    public double getOmega() {
        return omega.get();
    }

    public DoubleProperty omegaProperty() {
        return omega;
    }

    public void setOmega(double omega) {
        this.omega.set(omega);
    }
    public void resetObject(){
        setAcceleration(0);
        setVelocity(0);
        setPosition(0);
        setGamma(0);
        setOmega(0);
        setTheta(0);
    }

    @Override
    protected double calculateAcceleration(double appliedForce, Surface surface) {
        double angularAcceleration = calculateAngularAcceleration(appliedForce, surface);
        double acceleration;
        if (angularAcceleration == 0) {
            acceleration = appliedForce / getMass();
        } else {
            acceleration = angularAcceleration * getRadius();
        }
        return acceleration;
    }

    public double calculateAngularAcceleration(double appliedForce, Surface surface) {
        double frictionForce = calculateFrictionForces(appliedForce, surface);
        double angularAcceleration = frictionForce / (0.5 * getMass() * Math.pow(getRadius(), 2));
        setGamma(angularAcceleration);
        return angularAcceleration;
    }
    public void updateAngularPosition() {
        double deltaTime = 0.01;
        double currentAngularPosition = getTheta();
        double newAngularPosition = currentAngularPosition + getOmega() * deltaTime;
        setTheta(newAngularPosition);
    }
    public void updateAngularVelocity() {
        double deltaTime = 0.01;
        double currentAngularVelocity = getOmega();
        double newAngularVelocity = currentAngularVelocity + getGamma() * deltaTime;
        setOmega(newAngularVelocity);
    }
    @Override
    public double calculateFrictionForces(double appliedForce, Surface surface) {
        double gravitationalForce = calculateGravitationalForce();
        double normalForce = calculateNormalForce(gravitationalForce);

        double frictionForce = 0;

        if (Math.abs(appliedForce) <= 3 * normalForce * surface.getStaticCoefficient() && this.getVelocity() == 0) {
            frictionForce = -appliedForce / 3;
        } else if (Math.abs(appliedForce) > 3 * normalForce * surface.getStaticCoefficient() && this.getVelocity() == 0) {
            if (appliedForce > 0) {
                frictionForce = -normalForce * surface.getKineticCoefficient();
            } else {
                frictionForce = normalForce * surface.getKineticCoefficient();
            }
        } else if (this.getVelocity() < 0) {
            frictionForce = normalForce * surface.getKineticCoefficient();
        } else {
            frictionForce = -normalForce * surface.getKineticCoefficient();
        }
        return frictionForce;
    }

    public void updateAttribute(double appliedForce, Surface surface) {
        double deltaTime = 0.01;

        // Cập nhật gia tốc dựa trên lực tác dụng
        double acc = calculateAcceleration(appliedForce, surface);
        acceleration.set(acc);

        // Cập nhật vận tốc góc và vị trí góc của Cylinder
        updateAngularVelocity();
        if (getOmega() * (getOmega() + deltaTime * getGamma()) < 0) {
            setOmega(0);
        } else {
            setOmega(getOmega() + deltaTime * getGamma());
        }

        updateAngularPosition();
        if (getTheta() * (getTheta() + deltaTime * getOmega()) < 0) {
            setTheta(0);
        } else {
            setTheta(getTheta() + deltaTime * getOmega());
        }

        double currentVelocity = getVelocity();
        double newVelocity = currentVelocity + getAcceleration() * deltaTime;
        if (currentVelocity * newVelocity <= 0) {
            velocity.set(0); // Stop when velocity changes direction
        } else {
            velocity.set(newVelocity);
        }

        double linearVelocity = getRadius() * getOmega();
        double currentPosition = getPosition();
        double newLinearPosition = currentPosition + linearVelocity * deltaTime;
        setPosition(newLinearPosition);
    }
}