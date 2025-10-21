package br.com.ttech.backend.common.repository;

import br.com.ttech.backend.common.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
    User findByUsername(String username);
}
