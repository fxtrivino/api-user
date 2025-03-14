package com.bci.controller;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bci.dto.UsuarioDTO;
import com.bci.entity.Usuario;
import com.bci.service.UserService;


@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public UsuarioDTO createUser(@RequestBody Usuario usuario) {
        return userService.createUser(usuario);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Usuario> getUsers() {
        return userService.getUsers();
    }

    @GetMapping(path="/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Usuario getUserById(@PathVariable String id) {
    	return userService.getUserById(id);
    }

    @PutMapping(path="/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Usuario updateUser(@PathVariable String id, @RequestBody Usuario userDetails) {
        return userService.updateUser(id, userDetails);
    }

    @DeleteMapping(path="/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public void deleteUser(@PathVariable String id) {
    	userService.deleteUser(id);
    }
    
}
