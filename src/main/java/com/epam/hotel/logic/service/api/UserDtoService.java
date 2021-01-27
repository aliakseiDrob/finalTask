package com.epam.hotel.logic.service.api;

import com.epam.hotel.entity.dto.UserDto;
import com.epam.hotel.exception.ServicesException;
import java.util.List;

public interface UserDtoService {

    long findAmountPages(int itemsPerPage) throws ServicesException;

    List<UserDto> findPageUsers(int itemsPerPage, int page) throws ServicesException;
}
