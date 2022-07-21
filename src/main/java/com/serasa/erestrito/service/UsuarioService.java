package com.serasa.erestrito.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.serasa.erestrito.adapter.DozerConverter;
import com.serasa.erestrito.domain.entity.Usuario;
import com.serasa.erestrito.domain.vo.v1.UsuarioVO;
import com.serasa.erestrito.exception.ResourceNotFoundException;
import com.serasa.erestrito.repository.UsuarioRepository;

@Service
public class UsuarioService {
	
	@Autowired
	UsuarioRepository repository;

	@Autowired
  private PasswordEncoder encoder;
	
	public UsuarioVO inserir(UsuarioVO usuario) {
		var entity = DozerConverter.parseObject(usuario, Usuario.class);
		entity.setSenha(encoder.encode(usuario.getSenha()));

		var vo = DozerConverter.parseObject(repository.save(entity), UsuarioVO.class);
		return vo;
	}
	
	public List<UsuarioVO> buscarTodos() {
		return DozerConverter.parseListObject(repository.findAll(), UsuarioVO.class);
	}
	
	public UsuarioVO buscarPorId(Long id) {
		var entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Não foi encontrado registro com esse Id"));
		return DozerConverter.parseObject(entity, UsuarioVO.class);
	}
		
	public UsuarioVO atualizar(UsuarioVO usuario) {
		var entity = repository.findById(usuario.getKey())
				.orElseThrow(() -> new ResourceNotFoundException("Não foi encontrado registro com esse Id"));

		entity.setNome(usuario.getNome());
		entity.setSobrenome(usuario.getSobrenome());
		entity.setEmail(usuario.getEmail());
		entity.setUf(usuario.getUf());
		entity.setDataNascimento(usuario.getDataNascimento());
		entity.setUpdatedAt(LocalDateTime.now());
		
		var vo = DozerConverter.parseObject(repository.save(entity), UsuarioVO.class);
		return vo;
	}
	
	public void delete(Long id) {
		var entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Não foi encontrado registro com esse Id"));
		repository.delete(entity);
	}
}
