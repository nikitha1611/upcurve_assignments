package com.targetindia.programs;
import lombok.*;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)

public class Circle extends Shape{
    private double radius=1.0;

    Circle(double radius,String color,boolean filled)
    {
        super(color,filled);
        this.radius=radius;
    }
    public double getArea()
    {
        return Math.PI*radius*radius;
    }
    public double getPerimeter()
    {
        return 2*Math.PI*radius;
    }
    @Override
    public String toString()
    {
        return "A Circle with radius=" + radius + ", which is a subclass of " + super.toString();
    }

}
