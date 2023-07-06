package dsai.forcesimulation.Test;

import java.util.Scanner;
import dsai.forcesimulation.Model.Object.CubeBox;
import dsai.forcesimulation.Model.Object.Cylinder;
import dsai.forcesimulation.Model.Surface.Surface;

public class SimulationTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Nhập giá trị appliedForce từ người dùng
        System.out.print("Nhập giá trị appliedForce: ");
        double appliedForce = scanner.nextDouble();

        // Lựa chọn đối tượng (1 - CubeBox, 2 - Cylinder): ");
        System.out.print("Lựa chọn đối tượng (1 - CubeBox, 2 - Cylinder): ");
        int choice = scanner.nextInt();

        // Tạo đối tượng Surface
        Surface surface = new Surface(0.2, 0.1);

        switch (choice) {
            case 1:
                System.out.print("Enter mass value for CubeBox: ");
                double cubeMass = scanner.nextDouble();
                System.out.print("Enter side-length value for CubeBox: ");
                double cubeSide = scanner.nextDouble();

                CubeBox cubeBox = new CubeBox(cubeSide, cubeMass);
                cubeBox.calculateForces(appliedForce, surface);
                cubeBox.updateAttribute(appliedForce);
                // In thông tin về CubeBox...
                System.out.println("CubeBox:");
                System.out.println("Velocity: " + cubeBox.getVelocity());
                System.out.println("Acceleration: " + cubeBox.getAcceleration());
                System.out.println("Friction Force: " + cubeBox.getFrictionForce());
                double sumOfForce = appliedForce + cubeBox.getFrictionForce();
                System.out.println("Sum of Force: " + sumOfForce);
                break;
            case 2:
                System.out.print("Enter mass value for Cylinder: ");
                double cylinderMass = scanner.nextDouble();
                System.out.print("Enter radius value for Cylinder: ");
                double cylinderRadius = scanner.nextDouble();

                Cylinder cylinder = new Cylinder(cylinderRadius, cylinderMass);
                cylinder.calculateForces(appliedForce, surface);
                cylinder.updateAttribute(appliedForce);
                // In thông tin về Cylinder...
                System.out.println("Cylinder:");
                System.out.println("Velocity: " + cylinder.getVelocity());
                System.out.println("Acceleration: " + cylinder.getAcceleration());
                System.out.println("Friction Force: " + cylinder.getFrictionForce());
                sumOfForce = appliedForce + cylinder.getFrictionForce();
                System.out.println("Sum of Force: " + sumOfForce);
                break;
            default:
                System.out.println("Invalid selection!");
        }

        scanner.close();
    }
}
