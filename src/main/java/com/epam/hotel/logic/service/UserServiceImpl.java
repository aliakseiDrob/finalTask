package com.epam.hotel.logic.service;

import com.epam.hotel.dao.DaoHelper;
import com.epam.hotel.dao.DaoHelperFactory;
import com.epam.hotel.dao.api.ApplicationDao;
import com.epam.hotel.dao.api.UserDao;
import com.epam.hotel.entity.Application;
import com.epam.hotel.entity.User;
import com.epam.hotel.entity.enums.ApplicationStatus;
import com.epam.hotel.entity.enums.UserStatus;
import com.epam.hotel.exception.DaoException;
import com.epam.hotel.exception.ServicesException;
import com.epam.hotel.logic.service.api.UserService;


import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class UserServiceImpl implements UserService {
    private final DaoHelperFactory daoHelperFactory;

    public UserServiceImpl(DaoHelperFactory daoHelperFactory) {
        this.daoHelperFactory = daoHelperFactory;
    }

    @Override
    public Optional<User> login(final String login, final String password) throws ServicesException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            UserDao dao = daoHelper.createUserDao();
            return dao.findUserByLoginAndPassword(login, password);
        } catch (DaoException | SQLException e) {
            throw new ServicesException(e);
        }
    }

    @Override
    public void blockUnblockUser(Long userId) throws ServicesException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            UserDao dao = daoHelper.createUserDao();
            Optional<User> optionalUser = dao.findById(userId);
            User user = null;
            if (optionalUser.isPresent()) {
                user = optionalUser.get();
            } else {
                throw new ServicesException("user with id=" + userId + " doesn't exist");
            }
            UserStatus status = user.getStatus();
            String userStatus = status.toString();
            if (userStatus.equals("ACTIVE")) {
                user.setStatus(UserStatus.valueOf("BLOCKED"));
            }
            if (userStatus.equals("BLOCKED")) {
                user.setStatus(UserStatus.valueOf("ACTIVE"));
            }
            dao.save(user);
        } catch (DaoException | SQLException e) {
            throw new ServicesException(e);
        }
    }

    @Override
    public void deleteUser(Long userId) throws ServicesException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            UserDao userDao = daoHelper.createUserDao();
            ApplicationDao applicationDao = daoHelper.createApplicationDao();
            Optional<User> optionalUser = userDao.findById(userId);
            User user = null;
            if (optionalUser.isPresent()) {
                user = optionalUser.get();
            } else {
                throw new ServicesException("user with id=" + userId + " doesn't exist");
            }
            daoHelper.startTransaction();
            user.setStatus(UserStatus.valueOf("DELETED"));
            userDao.save(user);
            LocalDate beginDate = LocalDate.now().plusDays(1);

            List<Application> applicationList = applicationDao.findAllByUserIdAndTimePeriod(userId,beginDate);
            for (Application application : applicationList) {
                application.setStatus(ApplicationStatus.valueOf("REJECTED"));
                application.setRoomId(0L);
                application.setInvoice(new BigDecimal(0));
                applicationDao.save(application);
            }
            daoHelper.commitTransaction();
        } catch (DaoException | SQLException e) {
            throw new ServicesException(e);
        }

    }
}
