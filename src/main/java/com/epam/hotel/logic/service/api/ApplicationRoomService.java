package com.epam.hotel.logic.service.api;

import com.epam.hotel.exception.ServicesException;

/**
 * Interface for serving application objects according to the business logics of application
 */
public interface ApplicationRoomService {
    /**
     * Creates new Invoice by room id and application id
     *
     * @param roomId        room id
     * @param applicationId application id
     * @throws ServicesException in case of errors
     */
    void createInvoice(Long roomId, Long applicationId) throws ServicesException;
}
