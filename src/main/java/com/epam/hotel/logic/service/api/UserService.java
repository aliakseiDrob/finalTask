package com.epam.hotel.logic.service.api;

import com.epam.hotel.entity.User;
import com.epam.hotel.exception.ServicesException;

import java.util.Optional;

public interface UserService {

    Optional<User> login(final String login, final String password) throws ServicesException;

    void blockUnblockUser(Long userId) throws ServicesException;

    void deleteUser(Long userId) throws ServicesException;

}
