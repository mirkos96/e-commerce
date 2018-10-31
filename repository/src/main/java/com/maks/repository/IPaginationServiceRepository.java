package com.maks.repository;

import com.maks.repository.model.OrderItem;

import java.util.List;

public interface IPaginationServiceRepository {

    List<OrderItem> getCertainAmountOfOrderItemsForPage
            (Integer currentPageNumber);

    Integer countAmountOfPages();

    Integer countAmountOfPages(Integer numberOfItemsToDemonstrate);
}
