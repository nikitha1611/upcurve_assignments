package com.targetindia.programs;
import lombok.*;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)

public class Rectangle extends Shape{
    private double width=1.0;
    private double length=1.0;
    Rectangle(double width,double length,String color,boolean filled)
    {
        super(color,filled);
        this.width=width;
        this.length=length;
    }
    public double getArea()
    {
        return length*width;
    }
    public double getPerimeter()
    {
        return 2*(length+width);
    }
    @Override
    public String toString()
    {
        return "A Rectangle with width=" + width + " and length=" + length + ", which is a subclass of " + super.toString();
    }

}
