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
                minTimes = 1;  // Called at least once

                event.getExecutionStatus(anyInt);
                returns("success");
                maxTimes = 1;  // Called at most once

                event.printMessage(anyString);
                returns("status is success");
                minTimes = 1;  // Called at least once
            }
        };
        statusUpdater.updater(event);
    }

    @Test
    public void updaterFailure() throws StatusUpdateException {
        new Expectations() {
            {
                event.getRandomPosition();
                returns(1);
                minTimes = 1;  // Called at least once

                event.getExecutionStatus(anyInt);
                returns("");   // No specific return value needed
                maxTimes = 1;  // Called at most once
            }
        };
        statusUpdater.updater(event);
    }

    @Test
    public void updaterException() throws StatusUpdateException {
        new Expectations() {
            {
                event.getRandomPosition();
                returns(1);
                minTimes = 1;  // Called at least once

                event.getExecutionStatus(anyInt);
                returns("");   // No specific return value needed
                minTimes = 1;  // Called at least once

                event.printMessage(anyString);
                throws Exception; // Simulate any exception

            }
        };
        statusUpdater.updater(event);
    }
}
