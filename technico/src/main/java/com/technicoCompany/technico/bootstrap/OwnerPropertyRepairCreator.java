package com.technicoCompany.technico.bootstrap;

import com.technicoCompany.technico.base.BaseComponent;
import com.technicoCompany.technico.enumeration.PropertyType;
import com.technicoCompany.technico.enumeration.RepairStatus;
import com.technicoCompany.technico.enumeration.RepairType;
import com.technicoCompany.technico.enumeration.UserRole;
import com.technicoCompany.technico.model.Owner;
import com.technicoCompany.technico.model.Property;
import com.technicoCompany.technico.model.Repair;
import com.technicoCompany.technico.service.PropertyService;
import com.technicoCompany.technico.service.RepairService;
import com.technicoCompany.technico.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
@Profile("generate-owner-property-repair")
@RequiredArgsConstructor
public class OwnerPropertyRepairCreator extends BaseComponent implements CommandLineRunner {

    private final UserService userService;

    private final PropertyService propertyService;

    private final RepairService repairService;


    @Override
    public void run(String... args) throws Exception {

        List<Owner> startingOwners = List.of(
                Owner.builder().role(UserRole.PROPERTY_OWNER).userName("username1").password("test-password").firstName("Thomas").lastName("Varcdoo").address("kifisias 99").phoneNumber("2102102109").vatNumber("123456789").properties(new ArrayList<>()).email("test@gmail.com").build(),
                Owner.builder().role(UserRole.ADMIN).userName("adminUser1").password("secure-password1").firstName("Eva").lastName("Papadopoulou").address("Syntagma 20").phoneNumber("2103102200").vatNumber("987654321").properties(new ArrayList<>()).email("admin1@example.com").build(),
                Owner.builder().role(UserRole.PROPERTY_OWNER).userName("owner123").password("owner-pass").firstName("Dimitris").lastName("Karakostas").address("Vouliagmenis 50").phoneNumber("2104503450").vatNumber("223344556").properties(new ArrayList<>()).email("dimitris.k@example.com").build(),
                Owner.builder().role(UserRole.PROPERTY_OWNER).userName("realOwner").password("secure123").firstName("Maria").lastName("Katsani").address("Patission 180").phoneNumber("2109802200").vatNumber("445566778").properties(new ArrayList<>()).email("maria.k@example.com").build(),
                Owner.builder().role(UserRole.PROPERTY_OWNER).userName("owner2024").password("mypassword").firstName("Giorgos").lastName("Laskos").address("Athinas 1").phoneNumber("2101234500").vatNumber("112233445").properties(new ArrayList<>()).email("giorgos.l@example.com").build(),
                Owner.builder().role(UserRole.ADMIN).userName("adminElite").password("admin-secret").firstName("Sofia").lastName("Mavromati").address("Kifisias 200").phoneNumber("2105551234").vatNumber("998877665").properties(new ArrayList<>()).email("sofia.m@example.com").build(),
                Owner.builder().role(UserRole.PROPERTY_OWNER).userName("uniqueOwner").password("passcode").firstName("Nikos").lastName("Antoniou").address("Panepistimiou 5").phoneNumber("2107773333").vatNumber("667788990").properties(new ArrayList<>()).email("nikos.a@example.com").build(),
                Owner.builder().role(UserRole.PROPERTY_OWNER).userName("houseMaster").password("password2023").firstName("Eleni").lastName("Xatzaki").address("Piraeus 123").phoneNumber("2103334444").vatNumber("333444555").properties(new ArrayList<>()).email("eleni.h@example.com").build(),
                Owner.builder().role(UserRole.ADMIN).userName("superAdmin").password("adminpassword").firstName("Katerina").lastName("Filopoulou").address("Ampelokipoi 88").phoneNumber("2102221111").vatNumber("444555666").properties(new ArrayList<>()).email("katerina.f@example.com").build(),
                Owner.builder().role(UserRole.PROPERTY_OWNER).userName("propertyGuru").password("propPass").firstName("Panos").lastName("Georgiou").address("Kolonaki 10").phoneNumber("2101119999").vatNumber("555666777").properties(new ArrayList<>()).email("panos.g@example.com").build(),
                Owner.builder().role(UserRole.PROPERTY_OWNER).userName("ownerPlus").password("passPlus").firstName("Vasilis").lastName("Manos").address("Exarchia 15").phoneNumber("2108883333").vatNumber("111222333").properties(new ArrayList<>()).email("vasilis.m@example.com").build());


        List<Property> staringProperties = List.of(
                Property.builder().propertyIdentificationE9Number("E9-12345").propertyAddress("Kifisias 100, Athens").yearOfConstruction("2000").propertyType(PropertyType.DETACHED).propertyOwner(startingOwners.getFirst()).build(),
                Property.builder().propertyIdentificationE9Number("E9-12346").propertyAddress("Vouliagmenis 50, Athens").yearOfConstruction("1995").propertyType(PropertyType.FLAT).propertyOwner(startingOwners.getLast()).build(),
                Property.builder().propertyIdentificationE9Number("E9-12347").propertyAddress("Patission 20, Athens").yearOfConstruction("2010").propertyType(PropertyType.SEMI_DETACHED).propertyOwner(startingOwners.getFirst()).build(),
                Property.builder().propertyIdentificationE9Number("E9-12348").propertyAddress("Panepistimiou 15, Athens").yearOfConstruction("1980").propertyType(PropertyType.DETACHED).propertyOwner(startingOwners.getFirst()).build(),
                Property.builder().propertyIdentificationE9Number("E9-12349").propertyAddress("Syntagma Square 1, Athens").yearOfConstruction("2020").propertyType(PropertyType.FLAT).propertyOwner(startingOwners.getLast()).build(),
                Property.builder().propertyIdentificationE9Number("E9-12350").propertyAddress("Exarchia 5, Athens").yearOfConstruction("1990").propertyType(PropertyType.SEMI_DETACHED).propertyOwner(startingOwners.getLast()).build(),
                Property.builder().propertyIdentificationE9Number("E9-12351").propertyAddress("Kolonaki 22, Athens").yearOfConstruction("2005").propertyType(PropertyType.DETACHED).propertyOwner(startingOwners.getFirst()).build(),
                Property.builder().propertyIdentificationE9Number("E9-12352").propertyAddress("Piraeus 10, Athens").yearOfConstruction("1975").propertyType(PropertyType.FLAT).propertyOwner(startingOwners.getLast()).build(),
                Property.builder().propertyIdentificationE9Number("E9-12353").propertyAddress("Ampelokipoi 33, Athens").yearOfConstruction("2015").propertyType(PropertyType.SEMI_DETACHED).propertyOwner(startingOwners.getLast()).build(),
                Property.builder().propertyIdentificationE9Number("E9-12354").propertyAddress("Glyfada 88, Athens").yearOfConstruction("1998").propertyType(PropertyType.DETACHED).propertyOwner(startingOwners.getFirst()).build());

        List<Repair> startingRepairs = List.of(

                Repair.builder().scheduledRepairDate(LocalDateTime.of(2024, 12, 1, 9, 0)).repairStatus(RepairStatus.PENDING).repairType(RepairType.PAINTING).repairCost(new BigDecimal("1500.00")).repairAddress("Kifisias 100, Athens").property(staringProperties.getFirst()).workToBeDone("Repaint all interior walls").build(),
                Repair.builder().scheduledRepairDate(LocalDateTime.of(2024, 12, 5, 10, 30)).repairStatus(RepairStatus.IN_PROGRESS).repairType(RepairType.INSULATION).repairCost(new BigDecimal("2500.00")).repairAddress("Vouliagmenis 50, Athens").property(staringProperties.getFirst()).workToBeDone("Install thermal insulation on the roof").build(),
                Repair.builder().scheduledRepairDate(LocalDateTime.of(2024, 11, 28, 14, 0)).repairStatus(RepairStatus.COMPLETE).repairType(RepairType.FRAMES).repairCost(new BigDecimal("1800.00")).repairAddress("Patission 20, Athens").property(staringProperties.getLast()).workToBeDone("Replace window frames in the living room").build(),
                Repair.builder().scheduledRepairDate(LocalDateTime.of(2024, 11, 30, 8, 45)).repairStatus(RepairStatus.PENDING).repairType(RepairType.PLUMBING).repairCost(new BigDecimal("500.00")).repairAddress("Panepistimiou 15, Athens").property(staringProperties.getLast()).workToBeDone("Fix a leaking pipe in the bathroom").build(),
                Repair.builder().scheduledRepairDate(LocalDateTime.of(2024, 12, 10, 13, 15)).repairStatus(RepairStatus.IN_PROGRESS).repairType(RepairType.ELECTRICAL_WORK).repairCost(new BigDecimal("3000.00")).repairAddress("Syntagma Square 1, Athens").property(staringProperties.getLast()).workToBeDone("Upgrade the electrical wiring in the kitchen").build(),
                Repair.builder().scheduledRepairDate(LocalDateTime.of(2024, 11, 29, 11, 0)).repairStatus(RepairStatus.COMPLETE).repairType(RepairType.PAINTING).repairCost(new BigDecimal("1200.00")).repairAddress("Exarchia 5, Athens").property(staringProperties.getFirst()).workToBeDone("Paint exterior walls with weatherproof paint").build(),
                Repair.builder().scheduledRepairDate(LocalDateTime.of(2024, 12, 3, 15, 0)).repairStatus(RepairStatus.PENDING).repairType(RepairType.INSULATION).repairCost(new BigDecimal("3500.00")).repairAddress("Kolonaki 22, Athens").property(staringProperties.getFirst()).workToBeDone("Install soundproof insulation in the bedrooms").build(),
                Repair.builder().scheduledRepairDate(LocalDateTime.of(2024, 12, 2, 9, 30)).repairStatus(RepairStatus.IN_PROGRESS).repairType(RepairType.FRAMES).repairCost(new BigDecimal("2000.00")).repairAddress("Piraeus 10, Athens").property(staringProperties.getFirst()).workToBeDone("Install new aluminum window frames throughout the house").build(),
                Repair.builder().scheduledRepairDate(LocalDateTime.of(2024, 12, 8, 10, 0)).repairStatus(RepairStatus.COMPLETE).repairType(RepairType.PLUMBING).repairCost(new BigDecimal("700.00")).repairAddress("Ampelokipoi 33, Athens").property(staringProperties.getLast()).workToBeDone("Replace old plumbing pipes in the basement").build(),
                Repair.builder().scheduledRepairDate(LocalDateTime.of(2024, 12, 7, 16, 0)).repairStatus(RepairStatus.PENDING).repairType(RepairType.ELECTRICAL_WORK).repairCost(new BigDecimal("2800.00")).repairAddress("Glyfada 88, Athens").property(staringProperties.getLast()).workToBeDone("Install new electrical sockets in all rooms").build());

    }

}
