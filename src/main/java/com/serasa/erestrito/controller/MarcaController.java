package com.serasa.erestrito.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.serasa.erestrito.domain.entity.Marca;
import com.serasa.erestrito.domain.entity.TipoProduto;
import com.serasa.erestrito.service.MarcaService;
import com.serasa.erestrito.service.TipoProdutoService;

@RestController
@RequestMapping("/marca")
public class MarcaController {
	@Autowired
	MarcaService service;
	
	@GetMapping
	  public List<Marca> getAll() {
	    return service.listarTodos();
	  }

	  @GetMapping("/{id}")
	  public ResponseEntity<?> getById(@PathVariable Long id) {
	    Optional<Marca> marca = service.listarPorId(id);

	    if (marca.isPresent()) {
	      return ResponseEntity.ok(marca.get());
	    }

	    return ResponseEntity.notFound().build();
	  }

	  @PostMapping
	  @Transactional
	  public ResponseEntity<?> salvar(@RequestBody @Valid Marca payload, UriComponentsBuilder uriBuilder) {
		  Marca marca = service.salvar(payload);
		  URI uri = uriBuilder.path("/marca/{id}").buildAndExpand(marca.getId()).toUri();

	    return ResponseEntity.created(uri).body(marca); 
	  }

	  @PutMapping("/{id}")
	  @Transactional
	  public ResponseEntity<?> atualizar(@PathVariable Long id, @RequestBody @Valid Marca form) {
	    Optional<Marca> marca = service.listarPorId(id);

	    if (marca.isPresent()) {
	    	form.setId(id);
	    	service.salvar(form);
	      
	    	return ResponseEntity.ok(service.salvar(form));
	    }

	    return ResponseEntity.notFound().build();
	  }

	  @DeleteMapping("/{id}")
	  @Transactional
	  public ResponseEntity<?> deletar(@PathVariable Long id) {
	    Optional<Marca> marca = service.listarPorId(id);

	    if (marca.isPresent()) {
	      service.apagar(marca.get());
	      return ResponseEntity.ok(marca);
	    }
	    
	    return ResponseEntity.notFound().build();
	  }

}
