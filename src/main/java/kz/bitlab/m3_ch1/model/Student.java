package kz.bitlab.m3_ch1.model;

import lombok.*;
import org.springframework.stereotype.Component;

@Data
@AllArgsConstructor
//@NoArgsConstructor
@ToString
@Component
public class Student {
    private Long id;
    private String name;
    private String birthday;
    private String city;

    public Student() {
        System.out.println("bean Student was created");
    }
}
