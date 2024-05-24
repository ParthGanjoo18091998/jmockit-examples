package com.apigee.codecoverage;

import com.apigee.Environment;
import com.apigee.Organization;
import com.apigee.Messenger;
import com.apigee.Utils;
import mockit.*;
import org.junit.Test;

import java.sql.Timestamp;

public class MessageLoggerTest {

    @Mocked
    private Utils utils;
    @Mocked
    private Messenger messenger;
    @Mocked
    private Environment environment;
    @Mocked
    private Organization organization;

    @Test
    public void printMessage() throws Exception {
        new Expectations() {
            {
                messenger.message(anyInt);
                result = "message-" + anyInt;
                maxTimes = 1;
            }
        };
        new MessageLogger(messenger).printMessage(100);
    }

    @Test
    public void printTimestamp() {
        new Expectations() {
            {
                Utils.getMessageTimestamp(anyInt);
                result = new Timestamp(System.currentTimeMillis());
                minTimes = 1;
                Utils.getTypeMap(anyInt);
                result = "String-type";
                maxTimes = 1;
            }
        };
        new MessageLogger(messenger).printTimestamp(200, 1);
    }

    @Test
    public void printOrgEnv() {
        commonExpectations();
        new Expectations() {
            {
                messenger.getOrganization();
                result = organization;
                messenger.getEnvironment();
                result = environment;
                environment.getEnvironmentInfo();
                result = "my-env";
                organization.getOrganizationInfo();
                result = "my-org";
                messenger.getOrganization();
                minTimes = 1;
                messenger.getEnvironment();
                minTimes = 1;
            }
        };
        MessageLogger m = new MessageLogger(messenger);
        m.printOrg();
        m.printEnv();
    }

    @Test
    public void printOrgEnv2() {

        environment = new MockUp<Environment>() {
            @Mock
            public String getEnvironmentInfo() {
                return "my-env2";
            }
        }.getMockInstance();

        organization = new MockUp<Organization>() {
            @Mock
            public String getOrganizationInfo() {
                return "my-org2";
            }
        }.getMockInstance();
        
        commonExpectations();
        new Expectations() {
            {
                messenger.getEnvironment();
                result = environment;
                messenger.getOrganization();
                result = organization;
            }
        };
        MessageLogger m = new MessageLogger(messenger);
        m.printEnv();
        m.printOrg();
    }
}
