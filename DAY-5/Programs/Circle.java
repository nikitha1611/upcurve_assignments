package com.targetindia.programs;
import lombok.*;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Circle {
    private double radius;
    private String color;

    Circle(double radius)
    {
        this.radius=radius;
    }
    public double getArea()
    {
        return Math.PI*radius*radius;
    }

}
