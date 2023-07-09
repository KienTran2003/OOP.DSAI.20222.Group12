package dsai.forcesimulation.Model.Object;

import dsai.forcesimulation.Model.Surface.Surface;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

public abstract class MainObject {
    protected DoubleProperty mass = new SimpleDoubleProperty(50);
    protected DoubleProperty position = new SimpleDoubleProperty(0);
    protected DoubleProperty velocity = new SimpleDoubleProperty(0);
    protected DoubleProperty acceleration = new SimpleDoubleProperty(0);
    protected double frictionForce;


    public MainObject(double mass) {
        setMass(mass);
    }

    public double getMass() {
        return mass.get();
    }

    public void setMass(double mass) {
        this.mass.set(mass);
    }

    public DoubleProperty massProperty() {
        return mass;
    }

    public double getPosition() {
        return position.get();
    }

    public DoubleProperty positionProperty() {
        return position;
    }

    public void setPosition(double position) {
        this.position.set(position);
    }

    public double getVelocity() {
        return velocity.get();
    }

    public DoubleProperty velocityProperty() {
        return velocity;
    }

    public void setVelocity(double velocity) {
        this.velocity.set(velocity);
    }

    public double getAcceleration() {
        return acceleration.get();
    }

    public DoubleProperty accelerationProperty() {
        return acceleration;
    }

    public void setAcceleration(double acceleration) {
        this.acceleration.set(acceleration);
    }

    public double getFrictionForce() {
        return frictionForce;
    }

    public void setFrictionForce(double frictionForce) {
        this.frictionForce = frictionForce;
    }

    public void resetObject(){
        setAcceleration(0);
        setVelocity(0);
        setPosition(0);
    }

    public void updateAttribute(double appliedForce) {
        double deltaTime = 0.01;
        double acc = calculateAcceleration(appliedForce);
        acceleration.set(acc);
        velocity.set(velocity.get() + acceleration.get() * deltaTime);
        position.set(position.get() + velocity.get() * deltaTime);

        double currentVelocity = velocity.get();
        double newVelocity = currentVelocity + acceleration.get() * deltaTime;
        if (currentVelocity * newVelocity <= 0) {
            velocity.set(0); // Stop when velocity changes direction
        } else {
            velocity.set(newVelocity);
        }
    }
    double calculateGravitationalForce() {
        return this.getMass() * 10;
    }

    double calculateNormalForce(double gravitationalForce) {
        return gravitationalForce;
    }
    protected abstract double calculateAcceleration(double appliedForce);

    public abstract void calculateForces(double appliedForce, Surface surface);
}