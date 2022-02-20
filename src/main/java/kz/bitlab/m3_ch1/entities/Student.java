package kz.bitlab.m3_ch1.entities;

import lombok.*;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.List;

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

    @ManyToOne(fetch = FetchType.EAGER)
    private City city;

    @ManyToOne(fetch = FetchType.EAGER)
    private Faculty faculty;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Sport> sportList;
}
