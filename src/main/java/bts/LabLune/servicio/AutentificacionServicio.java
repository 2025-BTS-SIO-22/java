package bts.LabLune.servicio;

import bts.LabLune.modelo.User;
import bts.LabLune.modelo.UserDTO;
import bts.LabLune.repositorio.UserRepositorio;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AutentificacionServicio {


    @Autowired
    private UserRepositorio userRepositorio;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostConstruct
    public void init() {
        actualizarContraseñas();
    }

    public UserDTO autenticarUsuario(String username, String password) {
        Optional<User> optionalUser = userRepositorio.findByUsername(username);

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            if (passwordEncoder.matches(password, user.getPassword())) {
                return new UserDTO(user.getIdUser().longValue(), user.getUsername(), user.isAdmin());
            }
        }
        return null;
    }

    public void registrarUsuario(User user) {
        String encryptedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encryptedPassword);
        userRepositorio.save(user);
    }

    //  Método para actualizar una sola contraseña
    public void actualizarContraseña(Long userId, String nuevaContraseña) {
        Optional<User> userOptional = userRepositorio.findById(userId);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.setPassword(passwordEncoder.encode(nuevaContraseña));
            userRepositorio.save(user);
        }
    }

    // Método para actualizar todas las contraseñas si no están cifradas
    public void actualizarContraseñas() {
        List<User> usuarios = userRepositorio.findAll();

        for (User user : usuarios) {
            String contrasenaActual = user.getPassword();
            if (!contrasenaActual.startsWith("$2a$") && !contrasenaActual.startsWith("$2b$")) {
                String contrasenaCifrada = passwordEncoder.encode(contrasenaActual);
                user.setPassword(contrasenaCifrada);
                userRepositorio.save(user);
            }
        }
    }
}