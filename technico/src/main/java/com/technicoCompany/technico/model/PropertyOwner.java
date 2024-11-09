package com.technicoCompany.technico.model;

import com.technicoCompany.technico.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PropertyOwner extends User{


    private UserRole role = UserRole.PROPERTY_OWNER;
    private String ownerVatNumber;
    private String ownerName;
    private String ownerLastName;
    private String ownerAddress;
    private String ownerPhoneNumber;
    private String ownerEmail;
    private List<Property> properties;


}
