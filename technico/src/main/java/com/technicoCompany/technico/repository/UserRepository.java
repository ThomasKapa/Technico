package com.technicoCompany.technico.repository;

import com.technicoCompany.technico.enums.UserRole;
import com.technicoCompany.technico.model.PropertyOwner;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepository {

    private static List<PropertyOwner> owners = new ArrayList<>();

    static {
        owners.add(new PropertyOwner(UserRole.PROPERTY_OWNER,"123456789","Thomas","Thomas","Address 1","210-1234567","test.email@gmail.com",List.of()));
    }

}
