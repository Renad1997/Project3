package com.example.project3.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Employee {

    @Id
    private Integer id;

    @NotEmpty
    @Column(columnDefinition = "varchar(30) not null")
    private String position;

    @NotNull
    @Positive
    @Column(columnDefinition = "int not null")
    private int salary;

    @OneToOne
    @MapsId
    @JsonIgnore
    private User user;

}
