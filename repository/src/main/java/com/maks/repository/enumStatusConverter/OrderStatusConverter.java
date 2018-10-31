package com.maks.repository.enumStatusConverter;

import com.maks.repository.enumRole.EnumOrderStatus;
import org.springframework.stereotype.Component;

import javax.persistence.AttributeConverter;

@Component
public class OrderStatusConverter implements AttributeConverter<EnumOrderStatus, String> {
    @Override
    public String convertToDatabaseColumn(EnumOrderStatus orderStatusEnum) {
        switch (orderStatusEnum) {
            case NEW:
                return "NEW";
            case REVIEWING:
                return "REVIEWING";
            case IN_PROGRESS:
                return "IN_PROGRESS";
            case DELIVERED:
                return "DELIVERED";
            default:
                throw new IllegalArgumentException("illegal " + orderStatusEnum);
        }
    }

    @Override
    public EnumOrderStatus convertToEntityAttribute(String orderStatusString) {
        switch (orderStatusString.toUpperCase()) {
            case "NEW":
                return EnumOrderStatus.NEW;
            case "REVIEWING":
                return EnumOrderStatus.REVIEWING;
            case "IN_PROGRESS":
                return EnumOrderStatus.IN_PROGRESS;
            case "DELIVERED":
                return EnumOrderStatus.DELIVERED;
            default:
                throw new IllegalArgumentException("Illegal " + orderStatusString);
        }
    }
}
