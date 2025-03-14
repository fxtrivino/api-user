package com.bci.dto;

import java.util.Date;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class UsuarioDTO {
    private UUID id;
    private String name;
    private String email;
    private String password;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date created;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date modified;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date lastLogin;    
    private String token;   
    private Boolean isActive;
}
