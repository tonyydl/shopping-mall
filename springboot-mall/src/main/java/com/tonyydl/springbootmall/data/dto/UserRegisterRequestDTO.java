package com.tonyydl.springbootmall.data.dto;

import com.tonyydl.springbootmall.data.po.UserPO;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Builder(toBuilder = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRegisterRequestDTO {

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String password;

    public UserPO toPo() {
        Date now = new Date();
        return UserPO
                .builder()
                .email(email)
                .password(password)
                .createdDate(now)
                .lastModifiedDate(now)
                .build();
    }
}
