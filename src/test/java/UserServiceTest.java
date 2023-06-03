package org.example;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    UserService userService;
    @InjectMocks
    UserService service;
    User user;

    @BeforeEach
    public void setUp() {
        service = new UserService();
        user = new User("Jane", "fakePassword123", "JaneDoe@gmail.com");
        service.registerUser(user);
    }

    @AfterEach
    public void tearDown() {
        service = null;
        user = null;
    }

    @Test
    public void registerUserTest_userCreation() {
        User newUser = new User("John", "Password321", "JohnDoe@gmail.com");
        Boolean result = service.registerUser(newUser);
        assertTrue(result);
    }

    @Test
    public void registerUserTest_userAlreadyExists() {
        Boolean result = service.registerUser(user);
        assertFalse(result);
    }

    @Test
    public void loginUserTest_Login() {
        User loggedIn = service.loginUser("Jane", "fakePassword123");
        assertNotNull(loggedIn);
        assertEquals(user, loggedIn);
    }

    @Test
    public void registerUserTest_nullUser() {
        service.equals(null);
    }

    @Test
    public void loginUserTest_userNotFound() {
        User result = service.loginUser("N/A", "N/A");
        assertNull(result);
    }

    @Test
    public void LoginUserTest_wrongPassword() {
        User result = service.loginUser("Jane", "N/A");
        assertNull(result);
    }
}