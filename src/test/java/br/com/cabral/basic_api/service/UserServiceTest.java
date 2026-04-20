package br.com.cabral.basic_api.service;


import br.com.cabral.basic_api.domain.dto.users.UserCreateRequest;
import br.com.cabral.basic_api.domain.dto.users.UserResponse;
import br.com.cabral.basic_api.domain.dto.users.UserUpdateRequest;
import br.com.cabral.basic_api.domain.entity.User;
import br.com.cabral.basic_api.repository.UserRepository;
import br.com.cabral.basic_api.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import  org.mockito.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;


import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static  org.mockito.Mockito.*;


public class UserServiceTest {

    @Mock
    private UserRepository repository;

    @InjectMocks
    private UserService userService;

    public  UserServiceTest(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void  shouldCreatedUser(){
        UserCreateRequest userCreateRequest = new UserCreateRequest();
        userCreateRequest.Name = "Guilherme";
        userCreateRequest.Email = "Email@test.com";


        User savedUser = new User();
        savedUser.setId(1L);
        savedUser.setName(userCreateRequest.Name);
        savedUser.setEmail(userCreateRequest.Email);


        when(repository.save(any(User.class))).thenReturn(savedUser);


        var response = userService.createUser(userCreateRequest);

        assertNotNull(response);
        assertEquals(response.Name, "Guilherme");
        assertEquals(response.Email, "Email@test.com");

        verify(repository, times(1)).save(any(User.class));
    }


    @Test
    void listTest(){
        User userResponse1 =  new User();
        User userResponse2 =  new User();

        List<User> userResponseList = List.of(userResponse1,userResponse2);
        Pageable peage = PageRequest.of(0,100);

        Page<User> page = new PageImpl<>(userResponseList, peage,userResponseList.size());
        when(repository.findAll(peage)).thenReturn(page);

        List<UserResponse> userResponseList1 =  userService.listAll(peage);

        assertEquals(userResponseList1.size(),2);

        verify(repository,times(1)).findAll(peage);

    }


    @Test
    void validationUpdateService(){
        UserUpdateRequest userUpdateRequest  = new UserUpdateRequest();
        userUpdateRequest.Email = "teste@test.com";
        userUpdateRequest.Name = "test";

        User user = new User();
        user.setId(1L);
        user.setName( userUpdateRequest.Name);
        user.setEmail(userUpdateRequest.Email );


        when(repository.findById(1L)).thenReturn(Optional.of(user));
        when(repository.save(any(User.class))).thenReturn(user);

        var response = userService.updateUser(Long.parseLong("1") ,userUpdateRequest);

        assertNotNull(response);
        assertEquals(response.Id,1L);
        assertEquals(response.Name, userUpdateRequest.Name);
        assertEquals(response.Email,userUpdateRequest.Email);
    }

}
