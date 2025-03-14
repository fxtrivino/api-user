package com.bci.test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Optional;
import java.util.UUID;
import java.util.regex.Pattern;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import com.bci.dto.UsuarioDTO;
import com.bci.entity.Usuario;
import com.bci.exception.EmailAlreadyExistsException;
import com.bci.exception.InvalidEmailFormatException;
import com.bci.exception.InvalidPasswordFormatException;
import com.bci.repository.UserRepository;
import com.bci.service.UserService;
import com.bci.service.impl.UserServiceImpl;


@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;
    
    private UserService userService;
    
    private final String validPasswordRegex = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$";
    
    @BeforeEach
    void setUp() {
        userService = new UserServiceImpl(userRepository, validPasswordRegex);
        ReflectionTestUtils.setField(userService, "passwordPattern", Pattern.compile(validPasswordRegex));
    }

    @Test
    void createUser_ShouldThrowException_WhenEmailIsInvalid() {
        Usuario usuario = new Usuario();
        usuario.setEmail("invalid-email");
        usuario.setPassword("Password123");
        
        assertThrows(InvalidEmailFormatException.class, () -> userService.createUser(usuario));
    }

    @Test
    void createUser_ShouldThrowException_WhenEmailAlreadyExists() {
        Usuario usuario = new Usuario();
        usuario.setEmail("fxtrivino@gmail.com");
        usuario.setPassword("Password123");
        
        when(userRepository.findByEmail(usuario.getEmail())).thenReturn(Optional.of(new Usuario()));
        
        assertThrows(EmailAlreadyExistsException.class, () -> userService.createUser(usuario));
    }

    @Test
    void createUser_ShouldThrowException_WhenPasswordIsInvalid() {
        Usuario usuario = new Usuario();
        usuario.setEmail("fxtrivino@gmail.com");
        usuario.setPassword("123"); // Contraseña inválida
        
        when(userRepository.findByEmail(usuario.getEmail())).thenReturn(Optional.empty());
        
        assertThrows(InvalidPasswordFormatException.class, () -> userService.createUser(usuario));
    }
    
    @Test
    void createUser_ShouldSaveUser_WhenDataIsValid() {
        Usuario usuario = new Usuario();
        usuario.setEmail("fxtrivino@gmail.com");
        usuario.setPassword("Password123");
        
        when(userRepository.findByEmail(usuario.getEmail())).thenReturn(Optional.empty());
        when(userRepository.save(any(Usuario.class))).thenAnswer(invocation -> {
            Usuario savedUser = invocation.getArgument(0);
            savedUser.setId(UUID.randomUUID());
            return savedUser;
        });
        
        UsuarioDTO result = userService.createUser(usuario);
        
        assertNotNull(result);
        assertEquals(usuario.getEmail(), result.getEmail());
        assertNotNull(result.getId());
        assertNotNull(result.getToken());
    }
}