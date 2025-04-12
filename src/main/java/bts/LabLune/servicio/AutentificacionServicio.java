package bts.LabLune.servicio;

import bts.LabLune.modelo.User;
import bts.LabLune.repositorio.UserRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AutentificacionServicio {
    @Autowired
    private UserRepositorio userRepositorio;

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public boolean authenticate(String username, String password) {
        Optional<User> user = userRepositorio.findByUsername(username);

        if (user.isPresent()) {
            // Verificar si la contraseña es correcta
            return passwordEncoder.matches(password, user.get().getPassword());
        }
        return false; // Usuario no encontrado
    }
}
