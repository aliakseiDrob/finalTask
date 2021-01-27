package com.epam.hotel.logic.service.api;

import com.epam.hotel.exception.ServicesException;

public interface ApplicationRoomService {

    void createInvoice(Long roomId,Long applicationId) throws ServicesException;
}
