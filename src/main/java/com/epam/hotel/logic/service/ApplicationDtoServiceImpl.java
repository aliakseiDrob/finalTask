package com.epam.hotel.logic.service;

import com.epam.hotel.dao.api.ApplicationDtoDao;
import com.epam.hotel.dao.DaoHelper;
import com.epam.hotel.dao.DaoHelperFactory;
import com.epam.hotel.entity.dto.ApplicationDto;
import com.epam.hotel.exception.DaoException;
import com.epam.hotel.exception.ServicesException;

import java.sql.SQLException;
import java.util.List;

public class ApplicationDtoServiceImpl implements com.epam.hotel.logic.service.api.ApplicationDtoService {
    private final DaoHelperFactory daoHelperFactory;

    public ApplicationDtoServiceImpl(DaoHelperFactory daoHelperFactory) {
        this.daoHelperFactory = daoHelperFactory;
    }

    @Override
    public long findAmountPages(int itemsPerPage) throws ServicesException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            ApplicationDtoDao dao = daoHelper.createApplicationDtoDao();
            long totalInvoices = dao.findRowCount();
            if (totalInvoices <= itemsPerPage) {
                return 1;
            }
            return (int) Math.ceil(totalInvoices / (double) itemsPerPage);
        } catch (DaoException | SQLException e) {
            throw new ServicesException(e);
        }
    }

    @Override
    public List<ApplicationDto> findPageInvoices(int itemsPerPage, int page) throws ServicesException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            ApplicationDtoDao dao = daoHelper.createApplicationDtoDao();
            int begin = (page - 1) * itemsPerPage;
            return dao.findPaginatePage(itemsPerPage, begin);
        } catch (DaoException | SQLException e) {
            throw new ServicesException(e);
        }
    }

    @Override
    public List<ApplicationDto> findPageByUserId(Long id, int itemsPerPage, int page) throws ServicesException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            ApplicationDtoDao dao = daoHelper.createApplicationDtoDao();
            int begin = (page - 1) * itemsPerPage;
            return dao.findLimitByUserId(id, itemsPerPage, begin);
        } catch (DaoException | SQLException e) {
            throw new ServicesException(e);
        }
    }

    @Override
    public long findAmountPagesByUserId(long id, int itemsPerPage) throws ServicesException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            ApplicationDtoDao dao = daoHelper.createApplicationDtoDao();
            long totalInvoices = dao.findNumberRecordsByUserId(id);
            if (totalInvoices <= itemsPerPage) {
                return 1;
            }
            return (int) Math.ceil(totalInvoices / (double) itemsPerPage);
        } catch (DaoException | SQLException e) {
            throw new ServicesException(e);
        }
    }
}
