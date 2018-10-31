package com.maks.web.additionalService;

import com.maks.service.modelDto.UploadedOrderItemDto;

public interface IAdditionalOrderItemService {

    void saveNewPictureToFolder(UploadedOrderItemDto uploadedOrderItemDto);
}
