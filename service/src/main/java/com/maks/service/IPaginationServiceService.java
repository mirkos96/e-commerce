package com.maks.service;

import com.maks.service.modelDto.OrderItemDto;

import java.util.List;

public interface IPaginationServiceService {

    List<OrderItemDto> getCertainAmountOfItemsForPage
            (Integer currentPageNumber);

    List<OrderItemDto> getCertainAmountOfItemsForPage
            (Integer numberOfItemsOnPage, Integer currentPageNumber);

    Integer countTotalNumberOfPages();
}
