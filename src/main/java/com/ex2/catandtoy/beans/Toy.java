package com.ex2.catandtoy.beans;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.*;


@Entity
@Table(name = "toys")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Toy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false)
    @NotBlank
    private String name;
}
