package com.example.project3.DTO;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CustomerDTO {

//    @NotEmpty
//    @Size(min = 4, max = 10)
    private String username;
//
//    @NotEmpty
//    @Size(min = 6)
    private String password;

//    @NotEmpty
//    @Size(min = 2, max = 20)
    private String name;

//    @NotEmpty
//    @Email
    private String email;



//    @NotEmpty
//    @Pattern(regexp = "(\05|0)[0-9]{9}",message = "Phone Number must start with '05' and have exactly 10 digits")
    private String phoneNumber;


//    private Integer user_id;

}
