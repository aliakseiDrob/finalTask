package com.epam.hotel.logic.service;

import com.epam.hotel.dao.*;
import com.epam.hotel.dao.api.ApplicationDao;
import com.epam.hotel.entity.Application;
import com.epam.hotel.exception.DaoException;
import com.epam.hotel.exception.ServicesException;
import com.epam.hotel.logic.service.api.ApplicationService;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class ApplicationServiceImpl implements ApplicationService {
    private final DaoHelperFactory daoHelperFactory;

    public ApplicationServiceImpl(DaoHelperFactory daoHelperFactory) {
        this.daoHelperFactory = daoHelperFactory;
    }

    @Override
    public Application findById(Long id) throws ServicesException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            ApplicationDao dao = daoHelper.createApplicationDao();
            Optional<Application> optionalApplication = dao.findById(id);
            return optionalApplication.orElseThrow(() -> new ServicesException("application with id=" + id + "doesn't exist"));
        } catch (DaoException | SQLException e) {
            throw new ServicesException(e);
        }
    }

    @Override
    public void saveApplication(Application application) throws ServicesException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            ApplicationDao dao = daoHelper.createApplicationDao();
            dao.save(application);
        } catch (DaoException | SQLException e) {
            throw new ServicesException(e);
        }
    }

    @Override
    public int getDifferenceDate(Long id) throws ServicesException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            ApplicationDao dao = daoHelper.createApplicationDao();
            return dao.differenceBetweenDate(id);
        } catch (DaoException | SQLException e) {
            throw new ServicesException(e);
        }
    }

    @Override
    public long findAmountPages(int itemsPerPage) throws ServicesException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            ApplicationDao dao = daoHelper.createApplicationDao();
            long totalApplications = dao.findRowCount();
            if (totalApplications <= itemsPerPage) {
                return 1;
            }
            return (int) Math.ceil(totalApplications / (double) itemsPerPage);
        } catch (DaoException | SQLException e) {
            throw new ServicesException(e);
        }
    }

    @Override
    public List<Application> findPageApplications(int itemsPerPage, int page) throws ServicesException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            ApplicationDao dao = daoHelper.createApplicationDao();
            int begin = (page - 1) * itemsPerPage;
            return dao.findPaginatePage(itemsPerPage, begin);
        } catch (DaoException | SQLException e) {
            throw new ServicesException(e);
        }
    }

    @Override
    public List<Application> findPageApplicationsInProgress(int itemsPerPage, int page) throws ServicesException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            ApplicationDao dao = daoHelper.createApplicationDao();
            int begin = (page - 1) * itemsPerPage;
            return dao.findPaginatePageInProgress(itemsPerPage, begin);
        } catch (DaoException | SQLException e) {
            throw new ServicesException(e);
        }
    }

    @Override
    public long findAmountPagesInProgress(int itemsPerPage) throws ServicesException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            ApplicationDao dao = daoHelper.createApplicationDao();
            long totalApplications = dao.findRowCountInProgress();
            if (totalApplications <= itemsPerPage) {
                return 1;
            }
            return (int) Math.ceil(totalApplications / (double) itemsPerPage);
        } catch (DaoException | SQLException e) {
            throw new ServicesException(e);
        }
    }

    @Override
    public long findAmountPagesByUserId(long id, int itemsPerPage) throws ServicesException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            ApplicationDao dao = daoHelper.createApplicationDao();
            long totalApplications = dao.findRowCountByUserId(id);
            if (totalApplications <= itemsPerPage) {
                return 1;
            }
            return (int) Math.ceil(totalApplications / (double) itemsPerPage);
        } catch (DaoException | SQLException e) {
            throw new ServicesException(e);
        }
    }

    @Override
    public List<Application> findPageByUserId(long id, int itemsPerPage, int page) throws ServicesException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            ApplicationDao dao = daoHelper.createApplicationDao();
            int begin = (page - 1) * itemsPerPage;
            return dao.findPaginatePageByUserId(id, itemsPerPage, begin);
        } catch (DaoException | SQLException e) {
            throw new ServicesException(e);
        }
    }
}
