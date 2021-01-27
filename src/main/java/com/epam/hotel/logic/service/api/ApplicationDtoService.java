package com.epam.hotel.logic.service.api;

import com.epam.hotel.entity.dto.ApplicationDto;
import com.epam.hotel.exception.ServicesException;
import java.util.List;

public interface ApplicationDtoService {

    long findAmountPages(int itemsPerPage) throws ServicesException;

    List<ApplicationDto> findPageInvoices(int itemsPerPage, int page) throws ServicesException;

    List<ApplicationDto> findPageByUserId(Long id, int itemsPerPage, int page) throws ServicesException;

    long findAmountPagesByUserId(long id, int itemsPerPage) throws ServicesException;
}
