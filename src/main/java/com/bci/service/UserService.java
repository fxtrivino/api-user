package com.bci.service;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.bci.dto.UsuarioDTO;
import com.bci.entity.Usuario;

public interface UserService {
	public UsuarioDTO createUser(Usuario usuario);
	public List<Usuario> getUsers();
	public Usuario getUserById(String id);
	public Usuario updateUser(String id, Usuario userDetails);
	public void deleteUser(String id);
}
