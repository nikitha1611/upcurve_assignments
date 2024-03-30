package com.targetindia.programs;
import lombok.*;
@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper = true)
public class Cylinder extends Circle {
    private double height;

    Cylinder(double radius)
    {
        super(radius);
    }
    Cylinder(double radius,double height)
    {
        super(radius);
        this.height=height;
    }
    Cylinder(double radius,double height,String color)
    {
        super(radius,color);
        this.height=height;
    }
    public double getVolume()
    {
        return getArea()*height; //volume = pi*r*r*h
    }
}
