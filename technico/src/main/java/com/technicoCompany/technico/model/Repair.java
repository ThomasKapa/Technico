package com.technicoCompany.technico.model;

import com.technicoCompany.technico.enumeration.RepairStatus;
import com.technicoCompany.technico.enumeration.RepairType;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

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

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Property property;

    @Column(length = 500)
    private String workToBeDone;
}
