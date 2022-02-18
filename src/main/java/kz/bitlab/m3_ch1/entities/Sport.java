package kz.bitlab.m3_ch1.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "sports")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Sport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "sport_art")
    private String sportArt;
}
