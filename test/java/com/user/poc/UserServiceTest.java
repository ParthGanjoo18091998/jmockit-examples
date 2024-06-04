package com.user.poc;

import com.employee.poc.User;
import com.employee.poc.UserRepository;
import com.employee.poc.UserService;
import mockit.Expectations;
import mockit.MockUp;
import mockit.Mocked;
import mockit.Tested;
import org.junit.Test;

import static org.junit.Assert.assertNull;

public class UserServiceTest {

    @Tested
    private UserService userService;

    @Mocked
    private UserRepository userRepository;

    @Test
    public void testGetUserNameById_userExists() {
        new Expectations() {{
            userRepository.findUserById(1);
            result = new User(1, "John Doe");
        }};

        String userName = userService.getUserNameById(1);
        assertEquals("John Doe", userName);
    }

    @Test
    public void testGetUserNameById_userDoesNotExist() {
        new Expectations() {{
            userRepository.findUserById(1);
            result = null;
        }};

        String userName = userService.getUserNameById(1);
        assertNull(userName);
    }

    @Test
    public void testGetUserNameById_mockUpExample() {
        new MockUp<UserRepository>() {
            @mockit.Mock
            public User findUserById(int id) {
                if (id == 2) {
                    return new User(2, "Jane Doe");
                }
                return null;
            }
        };

        String userName = userService.getUserNameById(2);
        assertEquals("Jane Doe", userName);

        userName = userService.getUserNameById(3);
        assertNull(userName);
    }
}