package com.targetindia.programs;
import lombok.*;
@Getter
@Setter
@ToString(callSuper = true)

public class Square extends Rectangle{
    Square(double side)
    {
        super(side,side);
    }
    Square(double side,String color,boolean filled)
    {
        super(side,side,color,filled);
    }
    public void setSide(double side)
    {
        super.setWidth(side);
        super.setLength(side);
    }
    public double getSide()
    {
        return super.getWidth();
    }
    @Override
    public String toString()
    {
        return "A Square with side=" + getSide() + ", which is a subclass of " + super.toString();
    }

}
