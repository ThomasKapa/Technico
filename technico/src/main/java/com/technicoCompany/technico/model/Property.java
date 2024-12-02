package com.technicoCompany.technico.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.technicoCompany.technico.enumeration.PropertyType;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

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
    private String address;

    @Column(length = 4, nullable = false)
    private String yearOfConstruction;

    @Enumerated(EnumType.STRING)
    private PropertyType propertyType;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ownerId", nullable = false)
    @JsonIgnore
    private Owner owner;

    @OneToMany(mappedBy = "property",
            fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Repair> repairs;

}
