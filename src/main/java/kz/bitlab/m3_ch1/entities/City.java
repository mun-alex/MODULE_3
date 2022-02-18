package kz.bitlab.m3_ch1.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Entity
@Table(name = "cities")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "city_name")
    private String cityName;

    @Column(name = "city_code", length = 50)
    private String city_code;
}
