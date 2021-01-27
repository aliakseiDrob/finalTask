package com.epam.hotel;

import java.util.Map;

public class RequestContent {
    private Map <String,Object> requestAttributes;
    private Map <String, String[]> requestParameters;
    private Map <String,Object> sessionAttributes;


    public RequestContent(Map<String, Object> requestAttributes, Map<String, String[]> requestParameters, Map<String, Object> sessionAttributes) {
        this.requestAttributes = requestAttributes;
        this.requestParameters = requestParameters;
        this.sessionAttributes = sessionAttributes;
    }

    public Map<String, Object> getRequestAttributes() {
        return requestAttributes;
    }

    public void setRequestAttributes(Map<String, Object> requestAttributes) {
        this.requestAttributes = requestAttributes;
    }

    public Map<String, String[]> getRequestParameters() {
        return requestParameters;
    }

    public void setRequestParameters(Map<String, String[]> requestParameters) {
        this.requestParameters = requestParameters;
    }

    public Map<String, Object> getSessionAttributes() {
        return sessionAttributes;
    }

    public void setSessionAttributes(Map<String, Object> sessionAttributes) {
        this.sessionAttributes = sessionAttributes;
    }
}
