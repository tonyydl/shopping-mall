package com.tonyydl.springbootmall.data.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder(toBuilder = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserLoginRequestDTO {

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String password;
}
