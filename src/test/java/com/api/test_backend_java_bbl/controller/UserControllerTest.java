package com.api.test_backend_java_bbl.controller;

import com.api.test_backend_java_bbl.model.CreateNewUserRequest;
import com.api.test_backend_java_bbl.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private UserService userService;

    @Test
    void getListUsers() throws Exception {
        mockMvc.perform(get("/users"))
                .andExpect(status().isOk());
    }

    @Test
    void getUserDetail() throws Exception {
        mockMvc.perform(get("/users/12"))
                .andExpect(status().isOk());
    }

    @Test
    void createNewUser() throws Exception {
        String payload = """
                {
                  "name": "Leanne Graham",
                  "username": "Bret",
                  "email": "Sincere@april.biz",
                  "phone": "1-770-736-8031 x56442",
                  "website": "hildegard.org"
                }
                """;
        mockMvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(payload))
                .andExpect(status().isCreated());
    }

    @Test
    void createNewUserShouldValidateRequestBody() throws Exception {
        String payload = """
                {
                  "name": null,
                  "username": null,
                  "email": null,
                  "phone": "1-770-736-8031 x56442",
                  "website": "hildegard.org"
                }
                """;
        mockMvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(payload))
                .andExpect(status().isBadRequest());
    }

    @Test
    void updateUserDetail() throws Exception {
        String payload = """
                {
                  "name": "wira",
                  "username": "Bret",
                  "email": "Sincere@april.biz",
                  "phone": "1-770-736-8031 x56442",
                  "website": "hildegard.org"
                }
                """;
        mockMvc.perform(put("/users/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(payload))
                .andExpect(status().isOk());
    }

    @Test
    void updateUserDetailShouldValidateRequest() throws Exception {
        String payload = """
                {
                  "name": null,
                  "username": null,
                  "email": "",
                  "phone": "1-770-736-8031 x56442",
                  "website": "hildegard.org"
                }
                """;
        mockMvc.perform(put("/users/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(payload))
                .andExpect(status().isBadRequest());
    }

    @Test
    void deleteUser() throws Exception {
        mockMvc.perform(delete("/users/1"))
                .andExpect(status().isOk());
    }
}