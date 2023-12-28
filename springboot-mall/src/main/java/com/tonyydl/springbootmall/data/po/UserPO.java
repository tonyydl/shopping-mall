package com.tonyydl.springbootmall.data.po;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tonyydl.springbootmall.data.dto.UserDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Builder(toBuilder = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "`user`")
public class UserPO implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer userId;
    private String email;

    @JsonIgnore
    private String password;

    @Column(name = "created_date")
    private Date createdDate;
    @Column(name = "last_modified_date")
    private Date lastModifiedDate;

    public UserDTO toDTO() {
        return new UserDTO()
                .toBuilder()
                .userId(userId)
                .email(email)
                .createdDate(createdDate.getTime())
                .lastModifiedDate(lastModifiedDate.getTime())
                .build();
    }
}
