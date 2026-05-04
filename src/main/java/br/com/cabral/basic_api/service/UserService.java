package br.com.cabral.basic_api.service;

import br.com.cabral.basic_api.domain.dto.users.UserCreateRequest;
import br.com.cabral.basic_api.domain.dto.users.UserResponse;
import br.com.cabral.basic_api.domain.dto.users.UserUpdateRequest;
import br.com.cabral.basic_api.domain.entity.User;
import br.com.cabral.basic_api.exception.UsuarioNaoEncontradoException;
import br.com.cabral.basic_api.repository.UserRepository;
import br.com.cabral.basic_api.service.impl.UserServiceImpl;
import br.com.cabral.basic_api.service.validacao.UserValidacao;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class UserService implements UserServiceImpl {

    private final UserRepository userRepository;
    private final UserValidacao userValidacao;

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
                .orElseThrow(() -> new UsuarioNaoEncontradoException("Usuario não encontrado."));
    }

    @Transactional
    @Override
    public UserResponse createUser(UserCreateRequest user) {
        userValidacao.validaSeEmailExiste(user.Email);
        User userModel = new User();
        userModel.setName(user.Name);
        userModel.setEmail(user.Email);
        User saved = userRepository.save(userModel);
        return toResponse(saved);
    }

    @Transactional
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
        userRepository.deleteById(id);
    }

    private User get(Long id){
        return userRepository.findById(id).orElseThrow();
    }

    private UserResponse toResponse(User user){
         return new UserResponse(
                user.getId(),
                user.getName(),
                user.getEmail()
        );
    }
}
