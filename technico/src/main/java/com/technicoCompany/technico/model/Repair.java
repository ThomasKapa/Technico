package com.technicoCompany.technico.model;

import com.technicoCompany.technico.enums.RepairStatus;
import com.technicoCompany.technico.enums.RepairType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Repair {

    private Long RepairId;
    private LocalDateTime scheduledRepairDate;
    private RepairStatus repairStatus;
    private RepairType repairType;
    private BigDecimal repairCost;
    private String repairAddress;
    private PropertyOwner propertyOwner;
    private String workToBeDone;


}
