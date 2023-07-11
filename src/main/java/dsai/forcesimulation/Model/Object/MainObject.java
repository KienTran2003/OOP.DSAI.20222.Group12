package dsai.forcesimulation.Model.Object;

import dsai.forcesimulation.Model.Surface.Surface;

public abstract class MainObject {
    private double side;
    private double mass;
    private double position = 0;
    private double velocity = 0;
    private double acceleration = 0;

    public MainObject(double side, double mass) {
        this.side = side;
        this.mass = mass;
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
    public double normalForce() {
        return 10 * mass;
    }

    public void updateAttribute(double acceleration){
        //Update acceleration
        this.setAcceleration(acceleration);
        //Update position

        //Update velocity
        if (this.getVelocity()*(this.getVelocity() + 0.01 * acceleration) < 0 ){
            this.setVelocity(0);
        } else {
            this.setVelocity(this.getVelocity() + 0.01 * acceleration);
        }
        this.setPosition(this.getPosition() + 0.01*this.getVelocity());
    }
    public double calculateAcceleration(double appliedForce, double friction){
        return (appliedForce + friction)/this.getMass();
    }
    public abstract double calculateFriction(double appliedForce, double staticCoeffient, double kineticCoefficient);

}