package dsai.forcesimulation.Test;

import dsai.forcesimulation.Model.Object.CubeBox;
import dsai.forcesimulation.Model.Object.Cylinder;
import dsai.forcesimulation.Model.Surface.Surface;

public class Model {
    public static void main(String[] args){
        Surface surface = new Surface(0.5,0.4);
        CubeBox cubeBox = new CubeBox(20, 50);
        System.out.println("SideLength of CubicBox is: " + cubeBox.getSide());
        System.out.println("Mass of Cubicbox is: " + cubeBox.getMass());
        System.out.println("Position: " + cubeBox.getPosition() + " Velocity: " + cubeBox.getVelocity() + " Accelartion: " + cubeBox.getAcceleration());
        cubeBox.setMass(10);
        cubeBox.setSide(40);
        System.out.println("SideLength of CubicBox is: " + cubeBox.getSide());
        System.out.println("Mass of cubix box is: " + cubeBox.getMass());
        System.out.println("Friction: " + cubeBox.calculateFriction(100,surface.getStaticCoefficient(), surface.getKineticCoefficient()));
        System.out.println("Acceleration: " + cubeBox.calculateFriction(100, surface.getStaticCoefficient(), surface.getKineticCoefficient()));
        cubeBox.updateAttribute(100);
        System.out.println("Position: " + cubeBox.getPosition() + " Velocity: " + cubeBox.getVelocity() + " Accelartion: " + cubeBox.getAcceleration());


        System.out.println("CYLINDER");
        Cylinder cylinder = new Cylinder(50,50);
        System.out.println("SideLength of cylinder is: " + cubeBox.getSide());
        System.out.println("Mass of cylinder is: " + cubeBox.getMass());
        System.out.println("Position: " + cylinder.getPosition() + " Velocity: " + cylinder.getVelocity() + " Accelartion: " + cylinder.getAcceleration());
        System.out.println(cylinder.calculateGamma(50, cylinder.getMass(), cylinder.getSide()));
        cylinder.updateAttribute(200, 10);
        System.out.println("Position: " + cubeBox.getPosition() + " Velocity: " + cubeBox.getVelocity() + " Accelartion: " + cubeBox.getAcceleration());
        System.out.println("Gamma: " + cylinder.getGamma() + " Omega: "+cylinder.getOmega() + " Theta: " + cylinder.getTheta());
    }

}
