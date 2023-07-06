package dsai.forcesimulation.Model.Object;

import dsai.forcesimulation.Model.Surface.Surface;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

public class Cylinder extends MainObject{
    private DoubleProperty radius = new SimpleDoubleProperty();
    private double acceleration;
    private double frictionForce;

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

    @Override
    protected double calculateAcceleration(double appliedForce) {
        double acceleration = getFrictionForce() / (0.5 * getMass() * getRadius() * getRadius());
        setAcceleration(acceleration);
        return acceleration;
    }

    @Override
    public void calculateForces(double appliedForce, Surface surface) {
        double gravitationalForce = calculateGravitationalForce();
        double normalForce = calculateNormalForce(gravitationalForce);
        setFrictionForce(calculateFrictionForce(appliedForce, normalForce, surface));
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
            frictionForce = -appliedForce/3;
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
}
