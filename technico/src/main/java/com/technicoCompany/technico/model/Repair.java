package com.technicoCompany.technico.model;

import com.technicoCompany.technico.enums.RepairStatus;
import com.technicoCompany.technico.enums.RepairType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.stereotype.Component;
import java.math.BigDecimal;
import java.util.Date;


@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Repair {

    private Date scheduledRepairDate;
    private RepairStatus repairStatus;
    private RepairType repairType;
    private BigDecimal repairCost;
    private String repairAddress;
    private PropertyOwner propertyOwner;
    private String workToBeDone;

}
