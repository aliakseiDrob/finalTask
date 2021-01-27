package com.epam.hotel.dao.parsers;
import java.util.Map;

public interface Parser<T> {
    Map<String,Object> parse(T item);
}
