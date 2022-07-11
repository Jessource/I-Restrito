package com.serasa.erestrito.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.serasa.erestrito.domain.entity.Login;

@Repository
public interface LoginRepository extends JpaRepository<Login, Long> {
	
	Login findByUserName(String userName);

}
