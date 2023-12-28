package com.tonyydl.springbootmall.data.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder(toBuilder = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonPropertyOrder({
        "user_id",
        "email",
        "created_date",
        "last_modified_date"
})
public class UserDTO {
    @JsonProperty("user_id")
    private Integer userId;
    private String email;
    @JsonProperty("created_date")
    private long createdDate;
    @JsonProperty("last_modified_date")
    private long lastModifiedDate;
}
