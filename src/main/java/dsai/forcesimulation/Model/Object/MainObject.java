package dsai.forcesimulation.Model.Object;

import dsai.forcesimulation.Model.Surface.Surface;

public abstract class MainObject {
    private double side;
    private double mass;
    private double position = 0;
    private double velocity = 0;
    private double acceleration = 0;

    public MainObject(double side, double mass) {
        setSide(side);
        setMass(mass);
    }

    public double getVelocity() {
        return velocity;
    }

    public void setVelocity(double velocity) {
        this.velocity = velocity;
    }

    public double getAcceleration() {
        return acceleration;
    }

    public void setAcceleration(double acceleration) {
        this.acceleration = acceleration;
    }

    public double getSide() {
        return side;
    }

    public void setSide(double side) {
        this.side = side;
    }

    public double getMass() {
        return mass;
    }

    public void setMass(double mass) {
        this.mass = mass;
    }
    public double getPosition() {
        return position;
    }

    public void setPosition(double position) {
        this.position = position;
    }

    public void resetObject(){
        this.acceleration = 0;
        this.velocity = 0;
        this.position = 0;
    }

    public void updateAttribute(double appliedForce) {
        double deltaTime = 0.01;
        this.setAcceleration(calculateAcceleration(appliedForce));

        double currentVelocity = this.getVelocity();
        double newPosition = this.getPosition() + currentVelocity * deltaTime + 0.5 * acceleration * deltaTime * deltaTime;
        double newVelocity = currentVelocity + acceleration * deltaTime;

        if (currentVelocity * newVelocity < 0) {
            this.setVelocity(0); //stop
        } else {
            this.setVelocity(newVelocity);
        }
        this.setPosition(newPosition);
    }

    protected abstract double calculateAcceleration(double appliedForce);

    public abstract void calculateForces(double appliedForce, Surface surface);
}