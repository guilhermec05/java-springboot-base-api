package br.com.cabral.basic_api.service.impl;

import br.com.cabral.basic_api.domain.dto.users.UserCreateRequest;
import br.com.cabral.basic_api.domain.dto.users.UserResponse;
import br.com.cabral.basic_api.domain.dto.users.UserUpdateRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserServiceImpl {
    List<UserResponse> listAll(Pageable pageable);
    UserResponse getById(long id);
    UserResponse createUser(UserCreateRequest user);
    UserResponse updateUser(Long id , UserUpdateRequest userUpdate);
    void deletedUser(Long id);
}
