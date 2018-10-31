package com.maks.web.additionalService.Impl;

import com.maks.service.modelDto.UploadedOrderItemDto;
import com.maks.web.additionalService.IAdditionalOrderItemService;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;

@Component
public class AdditionalOrderItemService implements IAdditionalOrderItemService {

    private final static String PATH_TO_FOLDER =
            "C:\\Users\\Максим\\jd2_EE_project_main\\web\\src\\main\\webapp\\resources\\images\\";

    @Override
    public void saveNewPictureToFolder(UploadedOrderItemDto uploadedOrderItemDto) {
        try {
            FileUtils.writeByteArrayToFile(new File(PATH_TO_FOLDER + "" +
                            uploadedOrderItemDto.getOrderItemPictureName() + ".jpg"),
                    uploadedOrderItemDto.getFile().getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
