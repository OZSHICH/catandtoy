package com.ex2.catandtoy.beans;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.*;

@Entity
@Table(name = "cats")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data

public class Cat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false)
    @NotBlank
    private String name;
    @Column(nullable = false)

    private float weight;

    @OneToMany(cascade = CascadeType.PERSIST)
    @Singular
    private List<Toy> toys = new ArrayList<>();



}
