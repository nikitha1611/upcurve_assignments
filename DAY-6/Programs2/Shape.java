@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public abstract class Shape {
    private String color="red";
    private boolean filled=true;
    @Override
    public String toString()
    {
        return "A Shape with color of " + color + " and " + (filled ? "filled" : "Not filled");
    }

    public abstract double getArea();
    public abstract double getPerimeter();

}
