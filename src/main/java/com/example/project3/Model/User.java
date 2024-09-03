package com.example.project3.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "username should be not empty!")
    @Size(min = 4,max = 10,message = "Length must be between 4 and 10 characters")
    @Column(columnDefinition = "varchar(20) not null unique")
    private String username;

    @NotEmpty(message = "password should be not empty!")
    @Size(min = 6,message = "Length must be at least 6 characters.")
    @Column(columnDefinition = "varchar(300) not null")
    private String password;

    @NotEmpty(message = "name should be not empty!")
    @Size(min = 2 ,max = 20,message = "Length must be between 2 and 20 characters.")
    @Column(columnDefinition = "varchar(20) not null")
    private String name;

    @NotEmpty(message = "email should be not empty!")
    @Email
    @Column(columnDefinition = "varchar(30) not null unique")
    private String email;

    @NotEmpty(message = "role should be not empty!")
    @Pattern(regexp="^(CUSTOMER|EMPLOYEE|ADMIN)$",message = "Role must to be customer or employee or Admin")
    @Column(columnDefinition = "varchar(20) not null")
// @Check(constraints ="role In('CUSTOMER','EMPLOYEE','ADMIN')")
    private String role;

    @OneToOne(cascade = CascadeType.ALL,mappedBy = "user")
    @PrimaryKeyJoinColumn
    private Customer customer;

    @OneToOne(cascade = CascadeType.ALL,mappedBy = "user")
    @PrimaryKeyJoinColumn
    private Employee employee;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority(role));
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
