package com.serasa.erestrito.controller;

import java.net.URI;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.hibernate.cache.spi.DirectAccessRegion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.data.web.SortDefault.SortDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.serasa.erestrito.domain.dto.ComentarioProdutoDto;
import com.serasa.erestrito.domain.entity.ComentarioProduto;
import com.serasa.erestrito.domain.entity.Produto;
import com.serasa.erestrito.service.ComentarioProdutoService;
import com.serasa.erestrito.service.ProdutoService;

@RestController
@RequestMapping("/comentario-produto")
public class ComentarioProdutoController {

    @Autowired
    ComentarioProdutoService service;

    @Autowired
    ProdutoService produtoService;

    @GetMapping("/produto/{id}")
    public ResponseEntity<Object> getAll(
        @PathVariable Long id,
        @PageableDefault(page = 0, size = 10) 
        @SortDefaults({ 
            @SortDefault(sort = "id", direction = Direction.ASC)
        }) Pageable paginacao) {
        Page<ComentarioProduto> result = service.listarTodosPorProduto(id, paginacao);

        if (result == null) {
            return ResponseEntity.notFound().build();
        }
        //Aqui listo todos os comentarios associado aquele produto(id)

       return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        Optional<ComentarioProduto> comentarioP = service.listarPorId(id);

        if (comentarioP.isPresent()) {
            return ResponseEntity.ok(comentarioP.get());
        }

        return ResponseEntity.notFound().build();
        //Aqui retorno um comentario passando seu id.
    }

    @PostMapping
    @Transactional
    public ResponseEntity<?> salvar(@RequestBody @Valid ComentarioProdutoDto payload, UriComponentsBuilder uriBuilder) {
        Optional<Produto> produto = produtoService.listarPorId(payload.getProduto());

        if (produto.isPresent()) {
            ComentarioProduto comentarioP = new ComentarioProduto();
            comentarioP.setProduto(produto.get());
            comentarioP.setDescricao(payload.getDescricao());

            ComentarioProduto response = service.salvar(comentarioP);
            URI uri = uriBuilder.path("/comentario-produto/{id}").buildAndExpand(response.getId()).toUri();

            return ResponseEntity.created(uri).body(response);
        }

        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<?> atualizar(@PathVariable Long id, @RequestBody @Valid ComentarioProdutoDto form) {
        Optional<ComentarioProduto> comentarioP = service.listarPorId(id);

        if (comentarioP.isPresent()) {
            comentarioP.get().setDescricao(form.getDescricao());
            service.salvar(comentarioP.get());

            return ResponseEntity.ok(service.salvar(comentarioP.get()));
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> deletar(@PathVariable Long id) {
        Optional<ComentarioProduto> comentarioP = service.listarPorId(id);

        if (comentarioP.isPresent()) {
            service.apagar(comentarioP.get());
            return ResponseEntity.ok(comentarioP);
        }

        return ResponseEntity.notFound().build();
    }

}
