package com.apigee;

import java.util.HashMap;
import java.util.Map;

public class Singleton {

    private static Singleton instance = new Singleton();
    private Map <Integer, String> map;
    private Organization organization;
    private Environment environment;

    public static Singleton getInstance() {
        if (instance == null)
            instance = new Singleton();
        return instance;
    }

    public String message (int msgId) {
        return map.get(msgId);
    }

    public void messageComposer (int count) {
        map = new HashMap<Integer, String>();
        for (int i=0; i<count; i++) {
            map.put(i,"message-" + String.valueOf(i));
        }
    }

    public void setOrganization (Organization organization) {
        this.organization = organization;
    }

    public void setEnvironment (Environment environment) {
        this.environment = environment;
    }

    public Environment getEnvironment() {
        return environment;
    }

    public Organization getOrganization() {
        return organization;
    }
}
