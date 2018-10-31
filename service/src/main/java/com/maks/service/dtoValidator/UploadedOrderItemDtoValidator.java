package com.maks.service.dtoValidator;

import com.maks.service.modelDto.UploadedOrderItemDto;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
@Qualifier("uploadedOrderItemValidator")
public class UploadedOrderItemDtoValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return UploadedOrderItemDto.class.equals(aClass);
    }

    @Override
    public void validate(@Nullable Object o, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "orderItemName",
                "This field must be fulfilled");
        ValidationUtils.rejectIfEmpty(errors, "orderItemDescription",
                "This field must be fulfilled");
        ValidationUtils.rejectIfEmpty(errors, "orderItemPrice",
                "This field must be fulfilled");
        ValidationUtils.rejectIfEmpty(errors, "orderItemPictureName",
                "This field must be fulfilled");
    }
}
