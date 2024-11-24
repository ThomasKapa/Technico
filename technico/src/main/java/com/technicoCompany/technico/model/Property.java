package com.technicoCompany.technico.model;

import com.technicoCompany.technico.enumeration.PropertyType;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Entity
@Table(name = "property",
        indexes = {
                @Index(columnList = "propertyIdentificationE9Number"),
                @Index(columnList = "vatNumber")
        })
@SequenceGenerator(name = "idGenerator", sequenceName = "property_seq", initialValue = 1, allocationSize = 1)
public class Property extends BaseModel {

    @Column(nullable = false, unique = true)
    private String propertyIdentificationE9Number;

    @Column(length = 100, nullable = false)
    private String propertyAddress;

    @Column(length = 4, nullable = false)
    private String yearOfConstruction;

    @Enumerated(EnumType.STRING)
    private PropertyType propertyType;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "vatNumber", nullable = false)
    private Owner propertyOwner;

}
