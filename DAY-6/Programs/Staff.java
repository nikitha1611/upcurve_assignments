package com.targetindia.programs;
import lombok.*;
@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper = true)
public class Staff extends Person{
    private String school;
    private double pay;

    Staff(String name,String address,String school,double pay)
    {
        super(name,address);
        this.school=school;
        this.pay=pay;
    }
}
