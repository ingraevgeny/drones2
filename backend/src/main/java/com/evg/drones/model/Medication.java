package com.evg.drones.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@Builder(toBuilder = true)
@AllArgsConstructor(access = AccessLevel.PACKAGE)
public class Medication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;
    @Pattern(regexp = "^[A-Za-z0-9-_]*$", message = "allowed only letters, numbers, ‘-‘, ‘_’")
    private String name;
    private Integer weight;
    @Pattern(regexp = "^[A-Z0-9_]*$", message = "allowed only upper case letters, underscore and numbers")
    private String code;
    @Lob
    private byte[] image;
}
