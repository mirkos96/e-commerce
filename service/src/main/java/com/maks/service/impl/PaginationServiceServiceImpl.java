package com.maks.service.impl;

import com.maks.repository.IPaginationServiceRepository;
import com.maks.service.IPaginationServiceService;
import com.maks.service.converter.IConvertDtoAndPersistentEntities;
import com.maks.service.modelDto.OrderItemDto;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaginationServiceServiceImpl implements IPaginationServiceService {

    public static final Logger log = Logger.getLogger(PaginationServiceServiceImpl.class);
    private IPaginationServiceRepository paginationServiceRepository;
    private IConvertDtoAndPersistentEntities convertDtoAndPersistentEntities;

    @Autowired
    public PaginationServiceServiceImpl
            (IPaginationServiceRepository paginationServiceRepository, IConvertDtoAndPersistentEntities convertDtoAndPersistentEntities) {
        this.paginationServiceRepository = paginationServiceRepository;
        this.convertDtoAndPersistentEntities = convertDtoAndPersistentEntities;
    }

    @Override
    public List<OrderItemDto> getCertainAmountOfItemsForPage
            (Integer currentPageNumber) {
        return convertDtoAndPersistentEntities.convertOrderItemsIntoDto(
                paginationServiceRepository.getCertainAmountOfOrderItemsForPage(currentPageNumber));
    }

    @Override
    public Integer countTotalNumberOfPages() {
        return paginationServiceRepository.countAmountOfPages();
    }

    @Override
    public List<OrderItemDto> getCertainAmountOfItemsForPage
            (Integer numberOfItemsOnPage, Integer currentPageNumber) {
        return null;
    }
}
