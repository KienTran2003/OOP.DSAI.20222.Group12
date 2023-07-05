package dsai.forcesimulation.Model.Object;

import dsai.forcesimulation.Model.Surface.Surface;

public class Cylinder extends MainObject{
    private double radius;
    private Surface surface;
    private double acceleration;
    private double frictionForce;
    private double gamma = 0;
    private double omega = 0;
    private double theta = 0;

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

    public Cylinder(double radius, double mass) {
        super(radius, mass);
    }

    public double getRadius() {
        return radius;
    }
    public void resetObject() {
        this.setAcceleration(0);
        this.setVelocity(0);
        this.setPosition(0);
        this.setGamma(0);
        this.setOmega(0);
        this.setTheta(0);
    }
    @Override
    protected double calculateAcceleration(double appliedForce) {
        double acceleration = frictionForce / (0.5 * getMass() * getRadius() * getRadius());
        this.setAcceleration(acceleration);
        return acceleration;
    }

    @Override
    public void calculateForces(double appliedForce, Surface surface) {
        double gravitationalForce = calculateGravitationalForce();
        double normalForce = calculateNormalForce(gravitationalForce);
        double frictionForce = calculateFrictionForce(appliedForce, normalForce, surface);
        setFrictionForce(frictionForce);
    }

    private double calculateGravitationalForce() {
        return this.getMass() * 10;
    }

    private double calculateNormalForce(double gravitationalForce) {
        return gravitationalForce;
    }

    private double calculateFrictionForce(double appliedForce, double normalForce, Surface surface) {
        double frictionForce = 0;

        if (Math.abs(appliedForce) <= 3 * normalForce * surface.getStaticCoefficient() && this.getVelocity() == 0) {
            frictionForce = -appliedForce;
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

    @Override
    public void updateAttribute(double appliedForce) {
        super.updateAttribute(appliedForce);

        double newGamma = calculateAcceleration(appliedForce);

        if (this.getOmega() > 0 && this.getOmega() + newGamma < 0) {
            this.setTheta(0);
        } else {
            double newTheta = this.getTheta() + 0.01 * this.getOmega() + 0.5 * newGamma * 0.01 * 0.01;
            this.setTheta(newTheta);
        }
    }
}
