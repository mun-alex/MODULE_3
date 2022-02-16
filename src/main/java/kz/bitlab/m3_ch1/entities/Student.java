package kz.bitlab.m3_ch1.entities;

import lombok.*;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Entity
@Table(name = "students")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Component
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "student_name", length = 100)
    private String name;

    @Column(name = "student_birthday", length = 100)
    private String birthday;

    @Column(name = "student_city", length = 100)
    private String city;
}
