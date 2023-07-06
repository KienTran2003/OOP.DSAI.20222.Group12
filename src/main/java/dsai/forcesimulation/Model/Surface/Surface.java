package dsai.forcesimulation.Model.Surface;

public class Surface {
    private static final double MAX_STA_COEF = 1.0;
    private double staticCoefficient = 0;
    private double kineticCoefficient = 0;

    public Surface(){}
    public Surface(double staticCoefficient) {
        this.setStaticCoefficient(staticCoefficient);
        this.setKineticCoefficient(staticCoefficient/1.25);
    }

    public Surface(double staticCoefficient, double kineticCoefficient){
        this.setStaticCoefficient(staticCoefficient);
        this.setKineticCoefficient(kineticCoefficient);
    }

    public double getStaticCoefficient() {
        return staticCoefficient;
    }

    public void setStaticCoefficient(double staticCoefficient) {
        // Kiểm tra giá trị mới để đảm bảo nó không vượt quá MAX_STA_COEF
        if (staticCoefficient <= MAX_STA_COEF) {
            this.staticCoefficient = staticCoefficient;
        } else {
            // Nếu vượt quá giá trị tối đa, đặt giá trị là MAX_STA_COEF
            this.staticCoefficient = MAX_STA_COEF;
        }
    }

    public double getKineticCoefficient() {
        return kineticCoefficient;
    }

    public void setKineticCoefficient(double kineticCoefficient) {
        if (kineticCoefficient < 0) {
            this.kineticCoefficient = 0;
        } else if (kineticCoefficient >= getStaticCoefficient()) {
            this.kineticCoefficient= (getStaticCoefficient() - 0.001);
        } else {
            this.kineticCoefficient = kineticCoefficient;
        }
    }
}
