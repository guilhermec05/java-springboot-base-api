package br.com.cabral.basic_api.controller;

import br.com.cabral.basic_api.domain.dto.users.UserCreateRequest;
import br.com.cabral.basic_api.domain.dto.users.UserResponse;
import br.com.cabral.basic_api.service.UserService;
import br.com.cabral.basic_api.service.impl.UserServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static  org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private UserService userService;

    private final  ObjectMapper objectMapper = new ObjectMapper();;

    public  UserControllerTest(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldCreatedUser() throws  Exception{
        UserCreateRequest userCreateRequest = new UserCreateRequest();
        userCreateRequest.Name = "Test";
        userCreateRequest.Email = "test@email.com";

        UserResponse userResponse = new UserResponse();

        userResponse.Id = 1L;
        userResponse.Name = "Test";
        userResponse.Email = "test@email.com";

        when(userService.createUser(any())).thenReturn(userResponse);

        mockMvc.perform(
          post("/users")
                  .contentType("application/json")
                  .content(objectMapper.writeValueAsString(userResponse)))
                .andExpect(status().isCreated())  ;



    }
}
