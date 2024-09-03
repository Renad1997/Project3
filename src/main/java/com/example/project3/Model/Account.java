package com.example.project3.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty
    @Pattern(regexp = "^[0-9]{4}-[0-9]{4}-[0-9]{4}-[0-9]{4}$")
    @Column(columnDefinition = "varchar(20) not null")
    private String accountNumber;

    @NotNull(message = "balance should be not null!")
    @Positive
    @Column(columnDefinition = "int not null")
    private int balance;

    @AssertFalse
    @Column(columnDefinition = "boolean")
    private boolean isActive;

    @ManyToOne
//    @JoinColumn(name = "customer_id",referencedColumnName = "id")
    @JsonIgnore
    private Customer customer;

    public Account(String accountNumber, int balance, boolean isActive, Customer customer) {
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.isActive = isActive;
        this.customer = customer;
    }

}
