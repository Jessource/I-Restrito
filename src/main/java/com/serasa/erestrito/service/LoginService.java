package com.serasa.erestrito.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.serasa.erestrito.repository.LoginRepository;

@Service
public class LoginService implements UserDetailsService {
	
	@Autowired
	LoginRepository repository;
	
	public LoginService(LoginRepository repository) {
		this.repository = repository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		var user = repository.findByUserName(username);
		
			if(user != null) {
				return user;
			}else {
				throw new UsernameNotFoundException("Usuário "+username+" não localizado");
			}
	}
}
	
	


