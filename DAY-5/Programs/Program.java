package com.targetindia.programs;

public class Program {
    public static void main(String[] args) {
        Circle[] circles= {
                new Cylinder(12.34),
                new Cylinder(12.34,10.0),
                new Cylinder(12.34,10.0,"blue")
        };

        for (Circle circle : circles) {
            Cylinder c = (Cylinder) circle;
            System.out.println("Area: " + c.getArea() + ", Volume: " + c.getVolume());
        }
    }
}
