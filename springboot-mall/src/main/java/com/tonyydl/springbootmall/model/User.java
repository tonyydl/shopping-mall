package com.tonyydl.springbootmall.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder(toBuilder = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private Integer userId;
    private String email;

    @JsonIgnore
    private String password;
    
    private Date createdDate;
    private Date lastModifiedDate;
}
