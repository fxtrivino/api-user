package com.bci.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Object>  createUser(@RequestBody Usuario usuario) {
    	
    	UsuarioDTO usuarioDTO = userService.createUser(usuario);
        return new ResponseEntity<>(usuarioDTO, HttpStatus.CREATED);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Usuario>> getUsers() {
        List<Usuario> listUsuarios = userService.getUsers();
        return new ResponseEntity<>(listUsuarios, HttpStatus.OK);
    }

    @GetMapping(path="/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getUserById(@PathVariable String id) {
    	Usuario usuario = userService.getUserById(id);
    	return new ResponseEntity<>(usuario, HttpStatus.OK);
    }

    @PutMapping(path="/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> updateUser(@PathVariable String id, @RequestBody Usuario userDetails) {
    	Usuario usuario = userService.updateUser(id, userDetails);
        return new ResponseEntity<>(usuario, HttpStatus.OK);
    }

    @DeleteMapping(path="/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public void deleteUser(@PathVariable String id) {
    	userService.deleteUser(id);
    	new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
}
