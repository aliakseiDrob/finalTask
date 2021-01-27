package com.epam.hotel.logic.service;

import com.epam.hotel.dao.*;
import com.epam.hotel.dao.api.ApplicationDao;
import com.epam.hotel.dao.api.RoomDao;
import com.epam.hotel.entity.Application;
import com.epam.hotel.entity.Room;
import com.epam.hotel.entity.enums.ApplicationStatus;
import com.epam.hotel.exception.DaoException;
import com.epam.hotel.exception.ServicesException;
import com.epam.hotel.logic.service.api.ApplicationRoomService;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Optional;

public class ApplicationRoomServiceImpl implements ApplicationRoomService {
    private final DaoHelperFactory daoHelperFactory;

    public ApplicationRoomServiceImpl(DaoHelperFactory daoHelperFactory) {
        this.daoHelperFactory = daoHelperFactory;
    }

    @Override
    public void createInvoice(Long roomId, Long applicationId) throws ServicesException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            RoomDao roomDao = daoHelper.createRoomDao();
            ApplicationDao applicationDao = daoHelper.createApplicationDao();
            Optional<Room> optionalRoom = roomDao.findById(roomId);
            Room room = optionalRoom.orElseThrow(()->new ServicesException("room with id=" + roomId + "doesn't exist")); //TODO

            BigDecimal priceRoom = room.getPrice();
            Optional<Application> optionalApplication = applicationDao.findById(applicationId);
            Application application = optionalApplication.orElseThrow(()->new ServicesException("application with id=" + applicationId + "doesn't exist"));

            int differenceDate = applicationDao.differenceBetweenDate(applicationId);
            BigDecimal totalPrice = priceRoom.multiply(new BigDecimal(differenceDate));
            application.setStatus(ApplicationStatus.valueOf("CONFIRMED"));

            application.setRoomId(roomId);
            application.setInvoice(totalPrice);
            applicationDao.save(application);
        } catch (DaoException | ServicesException | SQLException e) {
            throw new ServicesException(e);
        }

    }
}
