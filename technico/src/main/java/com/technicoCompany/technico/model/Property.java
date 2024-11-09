package com.technicoCompany.technico.model;

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

public class Property {

    private Long propertyId;
    private String propertyIdentificationE9Number;
    private String propertyAddress;
    private String yearOfConstruction;
    private String propertyType;
    private PropertyOwner propertyOwner;
}