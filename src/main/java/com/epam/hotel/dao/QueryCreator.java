package com.epam.hotel.dao;

import java.util.Map;

public class QueryCreator {
    private final String table;

    public QueryCreator(String table) {
        this.table = table;
    }

    public String createUpdateQuery(Map<String, Object> fieldsMap) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("UPDATE ");
        stringBuilder.append(table);
        stringBuilder.append(" SET ");
        int count = 0;
        for (String key : fieldsMap.keySet()) {
            if (count < fieldsMap.size() - 1) {
                if (!key.equals("id")) {
                    stringBuilder.append(key);
                    stringBuilder.append("=?, ");
                }
                count++;
            } else {
                stringBuilder.append(key);
                stringBuilder.append("=? ");
            }
        }
        stringBuilder.append("WHERE id=?");
        return stringBuilder.toString();
    }

    public String createSaveQuery(Map<String, Object> fieldsMap) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("INSERT INTO ");
        stringBuilder.append(table);
        stringBuilder.append(" (");
        int count = 0;
        for (String key : fieldsMap.keySet()) {
            if (count < fieldsMap.size() - 1) {
                stringBuilder.append(key);
                stringBuilder.append(", ");
                count++;
            } else {
                stringBuilder.append(key);
            }
        }
        stringBuilder.append(") values (");
        for (int i = 0; i < fieldsMap.size(); i++) {
            if (i < fieldsMap.size() - 1) {
                stringBuilder.append("?");
                stringBuilder.append(", ");
            } else {
                stringBuilder.append("?");
            }
        }
        stringBuilder.append(")");
        return stringBuilder.toString();
    }
}
