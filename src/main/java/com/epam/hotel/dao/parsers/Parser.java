package com.epam.hotel.dao.parsers;

import java.util.Map;

/**
 * This interface  extracts fields data from Entity class object
 * and building Map that keys correspond with table field name in the database. When
 * value of this Map is actual object value
 *
 * @param <T> any entity class of the application
 */
public interface Parser<T> {
    /**
     * Extracts fields from accepted object and creates Map which holds values
     * of the object for the database, and its keys correspond to filed name
     * of the table in the specified db
     *
     * @param item entity class object
     * @return Map of table filed name and the value to be inserted
     */
    Map<String, Object> parse(T item);
}
