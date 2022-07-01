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

import com.serasa.erestrito.domain.entity.TipoProduto;
import com.serasa.erestrito.domain.entity.TipoRestricao;
import com.serasa.erestrito.repository.TipoProdutoRepository;
import com.serasa.erestrito.service.TipoProdutoService;
import com.serasa.erestrito.service.TipoRestricaoService;

@RestController
@RequestMapping("/tipo-produto")
public class TipoProdutoController {
	
	@Autowired
	TipoProdutoService service;
	
	@GetMapping
	  public List<TipoProduto> getAll() {
	    return service.listarTodos();
	  }

	  @GetMapping("/{id}")
	  public ResponseEntity<?> getById(@PathVariable Long id) {
	    Optional<TipoProduto> tProduto = service.listarPorId(id);

	    if (tProduto.isPresent()) {
	      return ResponseEntity.ok(tProduto.get());
	    }

	    return ResponseEntity.notFound().build();
	  }

	  @PostMapping
	  @Transactional
	  public ResponseEntity<?> salvar(@RequestBody @Valid TipoProduto payload, UriComponentsBuilder uriBuilder) {
		  TipoProduto tProduto = service.salvar(payload);
		  URI uri = uriBuilder.path("/tipo-produto/{id}").buildAndExpand(tProduto.getId()).toUri();

	    return ResponseEntity.created(uri).body(tProduto); 
	  }

	  @PutMapping("/{id}")
	  @Transactional
	  public ResponseEntity<?> atualizar(@PathVariable Long id, @RequestBody @Valid TipoProduto form) {
	    Optional<TipoProduto> tProduto = service.listarPorId(id);

	    if (tProduto.isPresent()) {
	    	form.setId(id);
	    	service.salvar(form);
	      
	    	return ResponseEntity.ok(service.salvar(form));
	    }

	    return ResponseEntity.notFound().build();
	  }

	  @DeleteMapping("/{id}")
	  @Transactional
	  public ResponseEntity<?> deletar(@PathVariable Long id) {
	    Optional<TipoProduto> tProduto = service.listarPorId(id);

	    if (tProduto.isPresent()) {
	      service.apagar(tProduto.get());
	      return ResponseEntity.ok(tProduto);
	    }
	    
	    return ResponseEntity.notFound().build();
	  }
	

}
