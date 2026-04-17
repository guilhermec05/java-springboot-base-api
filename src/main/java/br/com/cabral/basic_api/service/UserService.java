package br.com.cabral.basic_api.service;

import br.com.cabral.basic_api.domain.dto.users.UserCreateRequest;
import br.com.cabral.basic_api.domain.dto.users.UserResponse;
import br.com.cabral.basic_api.domain.dto.users.UserUpdateRequest;
import br.com.cabral.basic_api.domain.entity.User;
import br.com.cabral.basic_api.repository.UserRepository;
import br.com.cabral.basic_api.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService implements UserServiceImpl {

    @Autowired
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<UserResponse> listAll(Pageable pageable) {
        return userRepository
                .findAll(pageable)
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public UserResponse getById(long id) {
        return userRepository.findById(id)
                .map(this::toResponse)
                .orElse(null);
    }

    @Override
    public UserResponse createUser(UserCreateRequest user) {
        User userModel = new User();
        userModel.setName(user.Name);
        userModel.setEmail(user.Email);
        User saved = userRepository.save(userModel);
        return toResponse(saved);
    }

    @Override
    public UserResponse updateUser(Long id, UserUpdateRequest userUpdate) {

        User user = get(id);
        user.setName(userUpdate.Name);
        user.setEmail(userUpdate.Email);
        User saved =  userRepository.save(user);
        return toResponse(saved);
    }

    @Override
    public void deletedUser(Long id) {
        User user = get(id);
        userRepository.delete(user);
    }

    private User get(Long id){
        return userRepository.findById(id).orElseThrow();
    }

    private UserResponse toResponse(User user){
        UserResponse userResponse = new UserResponse();
        userResponse.Id = user.getId();
        userResponse.Name =  user.getName();
        userResponse.Email = user.getEmail();
        return  userResponse;
    }
}
