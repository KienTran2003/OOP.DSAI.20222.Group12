package dsai.forcesimulation.Model.Surface;

public class Surface {
    private double staticCoefficient = 0;
    private double kineticCoefficient = 0;

    public Surface(double staticCoefficient, double kineticCoefficient){
        this.staticCoefficient = staticCoefficient;
        this.kineticCoefficient = kineticCoefficient;
    }
    public double getStaticCoefficient() {
        return staticCoefficient;
    }

    public void setStaticCoefficient(double staticCoefficient) {
        this.staticCoefficient = staticCoefficient;
    }

    public double getKineticCoefficient() {
        return kineticCoefficient;
    }

    public void setKineticCoefficient(double kineticCoefficient) {
        this.kineticCoefficient = kineticCoefficient;
    }
}
