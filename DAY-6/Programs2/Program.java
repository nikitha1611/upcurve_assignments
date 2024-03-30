package com.targetindia.programs;
public class Program {
    public static void main(String[] args)
    {
        Shape[] shapes = new Shape[10];
        shapes[0] = new Circle(5.8, "Red", true);
        shapes[1] = new Rectangle(4.2, 6.0, "Blue", false);
        shapes[2] = new Square(3.1, "Cyan", true);
        shapes[3] = new Circle(2.5, "Grey", true);
        shapes[4] = new Rectangle(5.2, 7.0, "Orange", false);
        shapes[5] = new Square(2.0, "Purple", true);
        shapes[6] = new Circle(3.0, "Pink", false);
        shapes[7] = new Rectangle(3.5, 5.5, "Brown", true);
        shapes[8] = new Square(3.5, "Yellow", false);
        shapes[9] = new Circle(6.2, "White", true);

        for (Shape shape : shapes) {
            System.out.println(shape);
            System.out.println("Area: " + shape.getArea());
            System.out.println("Perimeter: " + shape.getPerimeter());
            System.out.println();
        }

    }
}
