package com.epam.hotel.logic.service;


import com.epam.hotel.dao.DaoHelper;
import com.epam.hotel.dao.DaoHelperFactory;
import com.epam.hotel.dao.api.ApplicationDao;
import com.epam.hotel.dao.api.RoomDao;
import com.epam.hotel.entity.Application;
import com.epam.hotel.entity.Room;
import com.epam.hotel.entity.enums.ApplicationStatus;
import com.epam.hotel.entity.enums.RoomStatus;
import com.epam.hotel.exception.DaoException;
import com.epam.hotel.exception.ServicesException;
import com.epam.hotel.logic.service.api.RoomService;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class RoomServiceImpl implements RoomService {
    private final DaoHelperFactory daoHelperFactory;

    public RoomServiceImpl(DaoHelperFactory daoHelperFactory) {

        this.daoHelperFactory = daoHelperFactory;
    }

    @Override
    public void addRoom(Room room) throws ServicesException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            RoomDao dao = daoHelper.createRoomDao();
            dao.save(room);
        } catch (DaoException | SQLException e) {
            throw new ServicesException(e);
        }
    }

    @Override
    public void blockUnblockRoom(Long id) throws ServicesException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            RoomDao dao = daoHelper.createRoomDao();
            Optional<Room> optionalRoom = dao.findById(id);
            Room room = optionalRoom.orElseThrow(()-> new ServicesException("Room with " + id + " doesn't exist"));

            RoomStatus actualStatus = room.getStatus();
            String status = actualStatus.toString();
            RoomStatus changedStatus;
            if ("AVAILABLE".equals(status)) {
                changedStatus = RoomStatus.valueOf("UNAVAILABLE");
            } else {
                changedStatus = RoomStatus.valueOf("AVAILABLE");
            }
            room.setStatus(changedStatus);
            dao.save(room);
        } catch (DaoException | SQLException e) {
            throw new ServicesException(e);
        }
    }

    @Override
    public void editRoom(Room room) throws ServicesException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            RoomDao roomDao = daoHelper.createRoomDao();
            ApplicationDao applicationDao = daoHelper.createApplicationDao();
            daoHelper.startTransaction();
            roomDao.save(room);
            Long id = room.getId();
            LocalDate beginDate = LocalDate.now().plusDays(1);
            LocalDate endDate = beginDate.plusDays(365);
            List<Application> applicationList = applicationDao.findAllByRoomIdAndTimePeriod(id,beginDate,endDate);
            for (Application application: applicationList) {
                application.setStatus(ApplicationStatus.IN_PROGRESS);
                application.setRoomId(0L);
                application.setInvoice(new BigDecimal("0"));
                applicationDao.save(application);
            }
            daoHelper.commitTransaction();
        } catch (DaoException | SQLException e) {
            throw new ServicesException(e);
        }
    }

    @Override
    public List<Room> getAllAvailableRooms(LocalDate check_in, LocalDate check_out) throws ServicesException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            RoomDao dao = daoHelper.createRoomDao();
            return dao.findAllAvailableRooms(check_in, check_out);
        } catch (DaoException | SQLException e) {
            throw new ServicesException(e);
        }

    }

    @Override
    public Room findById(Long id) throws ServicesException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            RoomDao dao = daoHelper.createRoomDao();
            Optional<Room> optionalRoom = dao.findById(id);
            Room room = null;
            if (optionalRoom.isPresent()) {
                room = optionalRoom.get();
            }
            return room;
        } catch (DaoException | SQLException e) {
            throw new ServicesException(e);
        }
    }

    @Override
    public long findAmountPages(int itemsPerPage) throws ServicesException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            RoomDao dao = daoHelper.createRoomDao();
            long totalRooms = dao.findRowCount();
            if (totalRooms <= itemsPerPage) {
                return 1;
            }
            return (int) Math.ceil(totalRooms / (double) itemsPerPage);
        } catch (DaoException | SQLException e) {
            throw new ServicesException(e);
        }
    }

    @Override
    public List<Room> findPageRooms(int itemsPerPage, int page) throws ServicesException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            RoomDao dao = daoHelper.createRoomDao();
            int begin = (page - 1) * itemsPerPage;
            return dao.findPaginatePage(itemsPerPage, begin);
        } catch (DaoException | SQLException e) {
            throw new ServicesException(e);
        }
    }
}
