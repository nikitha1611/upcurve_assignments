package com.targetindia.programs;
import lombok.*;
@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper = true)
public class Student extends Person{
    private String program;
    private int year;
    private double fee;
    Student(String name,String address,String program,int year,double fee)
    {
        super(name,address);
        this.program=program;
        this.year=year;
        this.fee=fee;
    }

}
