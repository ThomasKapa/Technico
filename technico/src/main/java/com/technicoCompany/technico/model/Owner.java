package com.technicoCompany.technico.model;

import gr.technico.app.enumeration.UserRole;
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


@Table(name = "owner",
        indexes = {@Index(columnList = "email"), @Index(columnList = "vatNumber")})

public class Owner extends BaseModel {

    @SequenceGenerator(name = "idGenerator",
            sequenceName = "owner_seq", initialValue = 1, allocationSize = 1)

    @Column(length = 50, nullable = false)
    private String userName;

    @Column(length = 50, nullable = false)
    private String password;

    @Enumerated(value = EnumType.STRING)
    private UserRole role;

    @Column(length = 9, nullable = false)
    private String vatNumber;

    @Column(length = 50, nullable = false)
    private String firstName;

    @Column(length = 50, nullable = false)
    private String lastName;

    @Column(length = 100, nullable = false)
    private String address;

    @Column(length = 20, nullable = false)
    private String phoneNumber;

    private String email;

    @ToString.Exclude
    @OneToMany(mappedBy = "owner",
            fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Property> properties;
}
