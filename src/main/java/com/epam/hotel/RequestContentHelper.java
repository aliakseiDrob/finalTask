package com.epam.hotel;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

public class RequestContentHelper {
    public RequestContent create(HttpServletRequest request) {
        Map<String, Object> requestAttributes = new HashMap<>();
        Map<String, String[]> requestParameters = request.getParameterMap();
        Map<String, Object> sessionAttributes = new HashMap<>();
        Enumeration<String> attributes = request.getSession().getAttributeNames();
        while (attributes.hasMoreElements()) {
            String attribute = (String) attributes.nextElement();
            Object value = request.getSession().getAttribute(attribute);
            sessionAttributes.put(attribute, value);
        }

        return new RequestContent(requestAttributes, requestParameters, sessionAttributes);
    }
}
