package com.bci.service;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.bci.dto.UsuarioDTO;
import com.bci.entity.Usuario;

public interface UserService {
	public UsuarioDTO createUser(@RequestBody Usuario usuario);
	public List<Usuario> getUsers();
	public Usuario getUserById(@PathVariable String id);
	public Usuario updateUser(@PathVariable String id, @RequestBody Usuario userDetails);
	public void deleteUser(@PathVariable String id);
}
