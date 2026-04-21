package br.com.cabral.basic_api.controller;

import br.com.cabral.basic_api.configuration.security.JwtService;
import br.com.cabral.basic_api.domain.dto.users.UserCreateRequest;
import br.com.cabral.basic_api.domain.dto.users.UserResponse;
import br.com.cabral.basic_api.domain.entity.User;
import br.com.cabral.basic_api.service.UserService;
import br.com.cabral.basic_api.service.impl.UserServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
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


    @Autowired
    private JwtService jwtService;



    @MockitoBean
    private UserService userService;

    private final  ObjectMapper objectMapper = new ObjectMapper();

    public  UserControllerTest(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldCreatedUser() throws  Exception{

        //String token = jwtService.generateToken("guilherme");

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
                 // .header("Authorization","Bearer " +token)
                  .contentType("application/json")
                  .content(objectMapper.writeValueAsString(userResponse)))

                .andExpect(status().isCreated())  ;



    }


    @Test
    void validationFormCreate() throws Exception {

        String token = jwtService.generateToken("guilherme");

        UserCreateRequest user = new UserCreateRequest();
        user.Name = "teste";
        user.Email = "tes";

        UserResponse userResponse = new UserResponse();

        userResponse.Id = 1L;
        userResponse.Name = user.Name;
        userResponse.Email = user.Email;

        when(userService.createUser(any())).thenReturn(userResponse);


        mockMvc.perform(post("/users")
                .header("Authorization","Bearer " +token)
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(user))
        ).andExpect(status().isBadRequest());



        user.Name = "";
        user.Email = "test@teste.com";


        mockMvc.perform(post("/users")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(user))
        ).andExpect(status().isBadRequest());



        user.Name = "teste";
        user.Email = "";


        mockMvc.perform(post("/users")
                .header("Authorization","Bearer " +token)
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(user))
        ).andExpect(status().isBadRequest());


        user.Name = "";
        user.Email = "";


        mockMvc.perform(post("/users")
                .header("Authorization","Bearer " +token)
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(user))
        ).andExpect(status().isBadRequest());
    }
}
