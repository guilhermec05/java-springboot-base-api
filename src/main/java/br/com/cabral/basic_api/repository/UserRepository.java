package br.com.cabral.basic_api.repository;

import br.com.cabral.basic_api.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository  extends JpaRepository<User,Long> {
}
