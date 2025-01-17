package com.apigee.codecoverage;

import com.apigee.Event;
import com.apigee.StatusUpdateException;
import org.junit.*;
import mockit.*;
import org.junit.runner.RunWith;
import mockit.integration.junit4.JMockit;


/**
 * StatusUpdater Tester.
 */

@RunWith(JMockit.class)
public class StatusUpdaterTest {

    @Mocked
    private Event event;
    @Tested
    private StatusUpdater statusUpdater;

    @Test
    public void updaterSuccess() throws StatusUpdateException {
        new Expectations() {
            {
                event.getRandomPosition();
                returns(1);
                event.getExecutionStatus(anyInt);
                returns("success");
                event.printMessage(anyString);
                returns("status is success");
            }
        };
        statusUpdater.updater(event);
    }

    @Test
    public void updaterFailure() throws StatusUpdateException {
        commonNonStrictExpectations();
        statusUpdater.updater(event);
    }

    @Test
    public void updaterException() throws StatusUpdateException {
        commonNonStrictExpectations();
        new NonStrictExpectations() {
            {
                event.getExecutionStatus(anyInt);
                returns("");
                event.printMessage(anyString);
                result = new StatusUpdateException();
            }
        };
        statusUpdater.updater(event);
    }

    private NonStrictExpectations commonNonStrictExpectations() throws StatusUpdateException {
        return new NonStrictExpectations() {
            {
                event.getRandomPosition();
                returns(-1);
                event.getExecutionStatus(anyInt);
                returns("failure");
                event.printMessage(anyString);
                returns("status is failure");
            }
        };
    }
}
