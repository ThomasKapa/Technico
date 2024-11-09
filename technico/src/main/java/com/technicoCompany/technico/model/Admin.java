package com.technicoCompany.technico.model;

import com.technicoCompany.technico.enums.UserRole;
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
public class Admin extends User{

    private UserRole role = UserRole.ADMIN;
    private String adminName;
    private String adminLastName;
    private String adminPhoneNumber;
    private String adminEmail;

}
