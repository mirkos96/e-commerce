package com.maks.repository.enumStatusConverter;

import com.maks.repository.enumRole.EnumUserRole;
import org.springframework.stereotype.Component;

import javax.persistence.AttributeConverter;

@Component
public class UserRoleConverter implements AttributeConverter<EnumUserRole, String> {

    @Override
    public String convertToDatabaseColumn(EnumUserRole enumRole) {
        switch (enumRole) {
            case ROLE_USER:
                return "ROLE_USER";
            case ROLE_ADMIN:
                return "ROLE_ADMIN";
            case ROLE_SUPERADMIN:
                return "ROLE_SUPERADMIN";
            default:
                throw new IllegalArgumentException("Illegal" + enumRole);
        }
    }

    @Override
    public EnumUserRole convertToEntityAttribute(String stringRole) {
        switch (stringRole) {
            case "ROLE_USER":
                return EnumUserRole.ROLE_USER;
            case "ROLE_ADMIN":
                return EnumUserRole.ROLE_ADMIN;
            case "ROLE_SUPERADMIN":
                return EnumUserRole.ROLE_SUPERADMIN;
            default:
                throw new IllegalArgumentException("Illegal" + stringRole);
        }
    }
}
