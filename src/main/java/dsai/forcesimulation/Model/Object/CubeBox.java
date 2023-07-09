package dsai.forcesimulation.Model.Object;

import dsai.forcesimulation.Model.Surface.Surface;

public class CubeBox extends MainObject{
    private double side;

    public CubeBox(double side, double mass) {
        super(mass);
        this.side = side;
    }

    @Override
    protected double calculateAcceleration(double appliedForce, Surface surface) {
        double frictionForce = calculateFrictionForces(appliedForce, surface);
        double netForce = appliedForce + frictionForce;
        return netForce / getMass();
    }

    @Override
    public double calculateFrictionForces(double appliedForce, Surface surface) {
        double gravitationalForce = calculateGravitationalForce();
        double normalForce = calculateNormalForce(gravitationalForce);

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