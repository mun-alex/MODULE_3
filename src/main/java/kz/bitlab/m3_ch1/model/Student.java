package kz.bitlab.m3_ch1.model;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Student {
    private Long id;
    private String name;
    private String birthday;
    private String city;
}
