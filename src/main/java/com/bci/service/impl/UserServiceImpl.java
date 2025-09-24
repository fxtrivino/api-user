package com.bci.service.impl;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.bci.dto.UsuarioDTO;
import com.bci.entity.Phone;
import com.bci.entity.Usuario;
import com.bci.exception.EmailAlreadyExistsException;
import com.bci.exception.InvalidEmailFormatException;
import com.bci.exception.InvalidPasswordFormatException;
import com.bci.exception.UserNotFoundException;
import com.bci.repository.UserRepository;
import com.bci.service.UserService;
import com.bci.util.JwtUtil;
import com.bci.util.Uuid;

@Service
public class UserServiceImpl implements UserService{
	private UserRepository userRepository;
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");
    private final Pattern passwordPattern;
    
    public UserServiceImpl(UserRepository userRepository, @Value("${password.regex}") String passwordRegex) {
        this.userRepository = userRepository;
        this.passwordPattern = Pattern.compile(passwordRegex);
    }
    
	@Override
	public UsuarioDTO createUser(Usuario usuario) {
    	if (!EMAIL_PATTERN.matcher(usuario.getEmail()).matches()) {
            throw new InvalidEmailFormatException("El formato del email es inválido");
        }
    	if (userRepository.findByEmail(usuario.getEmail()).isPresent()) {
            throw new EmailAlreadyExistsException("El correo ya está registrado");
        }
    	 if (!passwordPattern.matcher(usuario.getPassword()).matches()) {
             throw new InvalidPasswordFormatException("El formato de la contraseña es inválido");
         }
    	if (usuario.getPhones() != null) {
            for (Phone phone : usuario.getPhones()) {
                phone.setUser(usuario);
            }
        }
    	
    	usuario.setCreated(new Date());
    	usuario.setModified(new Date());
    	usuario.setLastLogin(new Date());
    	usuario.setIsActive(true);
    	
    	String token = JwtUtil.generateToken(usuario.getName());
    	
    	usuario.setToken(token);
    	
    	Usuario usuarioSaved = userRepository.save(usuario);
    	
    	UsuarioDTO usuarioDTO = new UsuarioDTO();
    	usuarioDTO.setId(usuarioSaved.getId());
    	usuarioDTO.setName(usuarioSaved.getName());
    	usuarioDTO.setEmail(usuarioSaved.getEmail());
    	usuarioDTO.setPassword(usuarioSaved.getPassword());
    	usuarioDTO.setCreated(usuarioSaved.getCreated());
    	usuarioDTO.setModified(usuarioSaved.getModified());
    	usuarioDTO.setLastLogin(usuarioSaved.getLastLogin());
    	usuarioDTO.setToken(usuarioSaved.getToken());
    	usuarioDTO.setIsActive(usuarioSaved.getIsActive());
    	
        return usuarioDTO;
	}

	@Override
	public List<Usuario> getUsers() {
		return userRepository.findAll();
	}

	@Override
	public Usuario getUserById(String id) {
		UUID uuid = Uuid.isValid(id);
    	return userRepository.findById(uuid).orElseThrow(() -> new UserNotFoundException("Usuario no encontrado"));
	}

	@Override
	public Usuario updateUser(String id, Usuario userDetails) {
    	
    	UUID uuid = Uuid.isValid(id);
    	
        Usuario usuario = userRepository.findById(uuid).orElseThrow(() -> new UserNotFoundException("Usuario no encontrado"));
        
        if (!EMAIL_PATTERN.matcher(userDetails.getEmail()).matches()) {
            throw new InvalidEmailFormatException("El formato del email es inválido");
        }
        if (!passwordPattern.matcher(userDetails.getPassword()).matches()) {
            throw new InvalidPasswordFormatException("El formato de la contraseña es inválido");
        }
          
        
        usuario.setName(userDetails.getName());
        usuario.setEmail(userDetails.getEmail());
        usuario.setPassword(userDetails.getPassword());
        //usuario.setPhones(userDetails.getPhones());
        
        List<Phone> existingPhones = usuario.getPhones();
        existingPhones.clear();
        if (userDetails.getPhones() != null) {
            for (Phone phone : userDetails.getPhones()) {
                phone.setUser(usuario);
                existingPhones.add(phone);
            }
        }

        return userRepository.save(usuario);
	}

	@Override
	public void deleteUser(String id) {
    	UUID uuid = Uuid.isValid(id);
    	userRepository.findById(uuid).orElseThrow(() -> new UserNotFoundException("Usuario no encontrado"));
        userRepository.deleteById(uuid);		
	}
	

}
