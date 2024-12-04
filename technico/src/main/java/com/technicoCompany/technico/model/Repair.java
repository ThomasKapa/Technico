package com.technicoCompany.technico.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.technicoCompany.technico.enumeration.RepairStatus;
import com.technicoCompany.technico.enumeration.RepairType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.apache.commons.lang3.builder.ToStringExclude;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Entity
@Table(name = "repair")
@SequenceGenerator(name = "idGenerator", sequenceName = "repair_seq", initialValue = 1, allocationSize = 1)

public class Repair extends BaseModel{

    @Column(nullable = false)
    private LocalDateTime scheduledRepairDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RepairStatus repairStatus;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RepairType repairType;

    @Column(precision = 10, scale = 2, nullable = false)
    private BigDecimal repairCost;

    @Column(length = 100, nullable = false)
    private String repairAddress;

    @Column(length = 500)
    private String workToBeDone;

    @ManyToOne(/*fetch =FetchType.LAZY,*/ optional = false)
    @JoinColumn(name = "propertyId", nullable = false)
    @NotNull(message = "Property cannot be null")
    @JsonIgnoreProperties("repairs")
    @ToStringExclude
    private Property property;

}
