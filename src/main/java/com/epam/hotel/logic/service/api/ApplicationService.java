package com.epam.hotel.logic.service.api;

import com.epam.hotel.entity.Application;
import com.epam.hotel.exception.ServicesException;

import java.util.List;

public interface ApplicationService {

    Application findById(Long id) throws ServicesException;

    void saveApplication(Application application) throws ServicesException;

    int getDifferenceDate(Long id) throws ServicesException;

    long findAmountPages(int itemsPerPage) throws ServicesException;

    List<Application> findPageApplications(int itemsPerPage, int page) throws ServicesException;

    List<Application> findPageApplicationsInProgress(int itemsPerPage, int page) throws ServicesException;

    long findAmountPagesInProgress(int itemsPerPage) throws ServicesException;

    long findAmountByUserId(long id, int itemsPerPage) throws ServicesException;

    List<Application> findPageByUserId(long id, int itemsPerPage, int page) throws ServicesException;
}
