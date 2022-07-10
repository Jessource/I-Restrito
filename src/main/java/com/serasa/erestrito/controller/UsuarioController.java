package com.serasa.erestrito.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.serasa.erestrito.domain.vo.v1.UsuarioVO;
import com.serasa.erestrito.service.UsuarioService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Usuario Endpoint")
@RestController
@RequestMapping("api/usuario/v1")
public class UsuarioController {

	@Autowired
	UsuarioService service;

	@CrossOrigin("localhost:8080") // permitido o acesso
	@RequestMapping(method = RequestMethod.GET, produces = { "application/json", "application/xml" })
	@Operation(summary = "Listar todas as pessoas")
	@ResponseStatus(value = HttpStatus.OK)
	public List<UsuarioVO> findAll() {
		List<UsuarioVO> usuariosVO = service.buscarTodos();
		usuariosVO.stream()
				.forEach(p -> p.add(linkTo(methodOn(UsuarioController.class).findById(p.getKey())).withSelfRel()));
		return usuariosVO;
	}

	@CrossOrigin({ "localhost:8080" }) // permitido o acesso
	@GetMapping(value = "/{id}", produces = { "application/json", "application/xml" })
	@ResponseStatus(value = HttpStatus.OK)
	public UsuarioVO findById(@PathVariable("id") Long id) {
		UsuarioVO usuarioVO = service.buscarPorId(id);
		usuarioVO.add(linkTo(methodOn(UsuarioController.class).findById(id)).withSelfRel());
		return usuarioVO;
	}

	@PostMapping(consumes = { "application/json", "application/xml" }, produces = { "application/json",
			"application/xml" })
	@ResponseStatus(value = HttpStatus.CREATED)
	public UsuarioVO create(@Valid @RequestBody UsuarioVO pessoa) {
		UsuarioVO usuarioVO = service.inserir(pessoa);
		usuarioVO.add(linkTo(methodOn(UsuarioController.class).findById(usuarioVO.getKey())).withSelfRel());
		return usuarioVO;
	}

	@PutMapping(consumes = { "application/json", "application/xml" }, produces = { "application/json",
			"application/xml" })
	@ResponseStatus(value = HttpStatus.OK)
	public UsuarioVO update(@Valid @RequestBody UsuarioVO usuario) {
		UsuarioVO usuarioVO = service.atualizar(usuario);
		usuarioVO.add(linkTo(methodOn(UsuarioController.class).findById(usuarioVO.getKey())).withSelfRel());
		return usuarioVO;
	}

	@DeleteMapping(value = "/{id}")
	@ResponseStatus(value = HttpStatus.OK)
	public void delete(@PathVariable("id") Long id) {
		service.delete(id);
	}

}
