package dsai.forcesimulation.Model.Object;

import dsai.forcesimulation.Model.Surface.Surface;

public class Cylinder extends MainObject implements RotatingObject{
    private double gamma = 0;
    private double omega = 0;
    private double theta = 0;
    public Cylinder(double radius, double mass){
        super(radius, mass);
    }
    public double getGamma() {
        return gamma;
    }
    public void setGamma(double gamma) {
        this.gamma = gamma;
    }
    public double getOmega() {
        return omega;
    }
    public void setOmega(double omega) {
        this.omega = omega;
    }
    public double getTheta() {
        return theta;
    }
    public void setTheta(double theta) {
        this.theta = theta;
    }
    @Override
    public void resetObject(){
        super.resetObject();
        this.setGamma(0);
        this.setOmega(0);
        this.setTheta(0);
    }
    @Override
    public double calculateFriction(double appliedForce, double staticCoeffient, double kineticCoefficient) {
        if (Math.abs(appliedForce) <= 3*this.normalForce()*staticCoeffient && this.getVelocity() == 0){
            return -appliedForce/3;
        } else if (Math.abs(appliedForce) > 3*this.normalForce()*staticCoeffient && this.getVelocity() == 0) {
            if (appliedForce > 0){
                return -this.normalForce()*kineticCoefficient;
            } else {
                return this.normalForce()*kineticCoefficient;
            }
        } else if (this.getVelocity() <0){
            return this.normalForce()*kineticCoefficient;
        } else {
            return -this.normalForce()*kineticCoefficient;
        }
    }
    public void updateAttribute(double acceleration, double newGamma){
        updateAttribute(acceleration);
        this.setGamma(newGamma);
        if (this.getOmega()*(this.getOmega() + 0.01*newGamma) < 0){
            this.setOmega(0);
        } else {
            this.setOmega(this.getOmega() + 0.01*newGamma);
        }
        if (this.getTheta()*(this.getTheta() + 0.01*this.getOmega()) < 0){
            this.setTheta(0);
        } else {
            this.setTheta(this.getTheta() + 0.01* this.getOmega());
        }
    }
    @Override
    public double calculateGamma(double friction, double mass, double radius) {
        return -2*friction/(mass*radius);
    }
}