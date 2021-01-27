package com.epam.hotel.dao;

import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;

public class QueryCreatorTest {
    private static final String TABLE_NAME = "room";
    private static final String EXPECTED_SAVE_QUERY = "INSERT INTO room (id, type, price, status, capacity, number) " +
            "values (?, ?, ?, ?, ?, ?)";
    private static final String EXPECTED_UPDATE_QUERY = "UPDATE room SET type=?, price=?, status=?, capacity=?, " +
            "number=? WHERE id=?";
    private final QueryCreator queryCreator = new QueryCreator(TABLE_NAME);

    @Test
    public void testCreateUpdateQueryShouldReturnValidQuery() {
        Map<String, Object> mapValues = createMapValues();
        String actualSaveQuery = queryCreator.createUpdateQuery(mapValues);
        Assert.assertEquals(EXPECTED_UPDATE_QUERY, actualSaveQuery);
    }

    @Test
    public void testCreateSaveQueryShouldreturnValidQuery() {
        Map<String, Object> mapValues = createMapValues();
        String actualSaveQuery = queryCreator.createSaveQuery(mapValues);
        Assert.assertEquals(EXPECTED_SAVE_QUERY, actualSaveQuery);
    }

    private Map<String, Object> createMapValues() {
        Map<String, Object> mapValues = new LinkedHashMap<>();
        mapValues.put("id", 1);
        mapValues.put("type", "COMFORT");
        mapValues.put("price", new BigDecimal("125"));
        mapValues.put("status", "AVAILABLE");
        mapValues.put("capacity", 2);
        mapValues.put("number", 21);
        return mapValues;
    }
}
