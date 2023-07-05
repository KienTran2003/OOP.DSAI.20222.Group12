package dsai.forcesimulation.Model.Object;

import dsai.forcesimulation.Model.Surface.Surface;

public class CubeBox extends MainObject{
    private Surface surface;
    private double frictionForce;

    public CubeBox(double side, double mass) {
        super(side, mass);
    }

    @Override
    protected double calculateAcceleration(double appliedForce) {
        double netForce = Math.abs(appliedForce - frictionForce);
        double acceleration = netForce / getMass();
        return acceleration;
    }

    @Override
    public void calculateForces(double appliedForce, Surface surface) {
        double gravitationalForce = calculateGravitationalForce();
        double normalForce = calculateNormalForce(gravitationalForce);
        double frictionForce = calculateFrictionForce(appliedForce, normalForce, surface);
        this.frictionForce = frictionForce;
    }

    private double calculateGravitationalForce() {
        double gravitationalForce = this.getMass() * 10;
        return gravitationalForce;
    }

    private double calculateNormalForce(double gravitationalForce) {
        double normalForce = gravitationalForce;
        return normalForce;
    }

    private double calculateFrictionForce(double appliedForce, double normalForce, Surface surface) {
        double frictionForce = 0;

        if (Math.abs(appliedForce) <= normalForce * surface.getStaticCoefficient() && this.getVelocity() == 0) {
            frictionForce = -appliedForce;
        } else if (Math.abs(appliedForce) > normalForce * surface.getStaticCoefficient() && this.getVelocity() == 0) {
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
