package com.technicoCompany.technico.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public abstract class User {
    @JsonIgnore
    private Long userId;
    @JsonIgnore
    private String userName;
    @JsonIgnore
    private String userPassWord;

}
