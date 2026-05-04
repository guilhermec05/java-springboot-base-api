package br.com.cabral.basic_api.service.validacao;

import br.com.cabral.basic_api.exception.UsuarioJaExisteException;
import br.com.cabral.basic_api.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class UserValidacao {

    private final UserRepository userRepository;

    public void validaSeEmailExiste(String email){
        if (userRepository.existsByEmail(email)){
            throw new UsuarioJaExisteException("Usuario ja existe no banco.");
        }
    }
}