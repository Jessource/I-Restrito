package com.serasa.erestrito.controller;

import static org.springframework.http.ResponseEntity.ok;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.serasa.erestrito.security.CredenciaisContaVO;
import com.serasa.erestrito.security.jwt.TokenService;

import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name="Authentication Endpoint")
@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

	@Autowired	
	private AuthenticationManager authenticationManager;

	@Autowired
	private TokenService tokenService;

	@PostMapping(produces = { "application/json", "application/xml" }, 
			consumes = { "application/json",	"application/xml" })
	public ResponseEntity<?> signin(@RequestBody CredenciaisContaVO cred) {
		
		try {
			var username = cred.getUsername();
			var password = cred.getPassword();
			
			Authentication authentication = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(username, password));

			String token = tokenService.generateToken(authentication);
			
			Map<Object, Object> model = new HashMap<>();
			model.put("token", token);
			model.put("type", "Bearer");
			
			return ok(model);
		} catch (AuthenticationException e) {
			throw new BadCredentialsException("Usuário ou senha inválidos");
		}
	}

}
