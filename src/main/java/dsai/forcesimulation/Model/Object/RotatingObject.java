package dsai.forcesimulation.Model.Object;

import dsai.forcesimulation.Model.Surface.Surface;

public interface RotatingObject {
    double calculateGamma(double friction, double mass, double radius);
}