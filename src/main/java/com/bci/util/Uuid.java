package com.bci.util;

import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.bci.exception.InvalidUUIDFormatException;

public class Uuid {
    
	private static final Pattern UUID_PATTERN = Pattern.compile("^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[1-5][0-9a-fA-F]{3}-[89abAB][0-9a-fA-F]{3}-[0-9a-fA-F]{12}$");

	private Uuid() {}
    
	public static UUID isValid(String uuid) {
        Matcher matcher = UUID_PATTERN.matcher(uuid);
        if (!matcher.matches()) {
        	throw new InvalidUUIDFormatException("El formato del UUID es inválido");
        }
        
        return UUID.fromString(uuid);
    }
}
