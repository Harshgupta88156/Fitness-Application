package com.fitness.dto;


import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RegisterRequest {
    @NotBlank(message = "Email is required")
    @Email(message = "Entered email is not correct")
    private String email;

    @NotBlank(message = "Password is required")
    @Size(min = 6, message = "Password must consist atleast 6 character")
    private String password;
    private String fullName;
    private String lastName;


}
