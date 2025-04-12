package bts.LabLune.repositorio;

import bts.LabLune.modelo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepositorio extends JpaRepository<User,Integer> {
    Optional<User> findByUsername(String username);
}
