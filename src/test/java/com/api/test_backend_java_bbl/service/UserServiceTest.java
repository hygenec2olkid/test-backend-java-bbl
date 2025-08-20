package com.api.test_backend_java_bbl.service;

import com.api.test_backend_java_bbl.exception.NotFoundException;
import com.api.test_backend_java_bbl.model.CreateNewUserRequest;
import com.api.test_backend_java_bbl.model.UpdateUserDetail;
import com.api.test_backend_java_bbl.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setUp(){
        userService.loadUsers();
    }

    @Test
    void getListUser() {
        List<User> userList = userService.getListUser();

        // assert
        assertNotNull(userList);
        assertEquals(10,userList.size());
    }

    @Test
    void findByUserId() {
        long userId = 1L;
        User user = userService.findByUserId(userId);

        // assert
        assertNotNull(user);
        assertEquals("Leanne Graham",user.getName());
        assertEquals("Bret",user.getUsername());
        assertEquals("Sincere@april.biz",user.getEmail());
        assertEquals("1-770-736-8031 x56442",user.getPhone());
        assertEquals("hildegard.org",user.getWebsite());
    }

    @Test
    void findByUserIdShouldThrow() {
        long userId = 999L;
        Exception exception = assertThrows(NotFoundException.class,()-> userService.findByUserId(userId));

        // assert
        assertEquals("User not found",exception.getMessage());
    }

    @Test
    void createNewUser() {
        CreateNewUserRequest mockRequest = new CreateNewUserRequest("name","username","email","phone","website");
        int total = userService.getListUser().size();
        User newUser =  userService.createNewUser(mockRequest);

        // assert
        assertNotNull(newUser);
        assertEquals("name",newUser.getName());
        assertEquals("username",newUser.getUsername());
        assertEquals("email",newUser.getEmail());
        assertEquals("phone",newUser.getPhone());
        assertEquals("website",newUser.getWebsite());
        assertEquals(total+1 ,userService.getListUser().size());
    }

    @Test
    void updateUser() {
        UpdateUserDetail mockRequest = new UpdateUserDetail("name","username","email","phone","website");
        Long userId = 1L;

        // assert
        User user = userService.updateUser(userId,mockRequest);
        assertNotNull(user);
        assertEquals("name",user.getName());
        assertEquals("username",user.getUsername());
        assertEquals("email",user.getEmail());
        assertEquals("phone",user.getPhone());
        assertEquals("website",user.getWebsite());
    }

    @Test
    void updateUserShouldThrow() {
        UpdateUserDetail mockRequest = new UpdateUserDetail("name","username","email","phone","website");
        Long userId = 999L;

        Exception exception = assertThrows(NotFoundException.class,()->userService.updateUser(userId,mockRequest));

        // assert
        assertEquals("User not found",exception.getMessage());
    }

    @Test
    void deleteUser() {
        Long userId = 1L;
        int total = userService.getListUser().size();

        userService.deleteUser(userId);

        assertEquals(total - 1 ,userService.getListUser().size());
    }

    @Test
    void deleteUserShouldThrow() {
        Long userId = 999L;

        Exception exception = assertThrows(NotFoundException.class,()->userService.deleteUser(userId));

        assertEquals("User not found" ,exception.getMessage());
    }
}