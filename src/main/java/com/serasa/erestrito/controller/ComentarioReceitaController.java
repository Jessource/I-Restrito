package com.serasa.erestrito.controller;

import java.net.URI;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.serasa.erestrito.domain.dto.ComentarioReceitaDto;
import com.serasa.erestrito.domain.entity.ComentarioReceita;
import com.serasa.erestrito.domain.entity.Receita;
import com.serasa.erestrito.domain.entity.Usuario;
import com.serasa.erestrito.security.jwt.CurrentUser;
import com.serasa.erestrito.service.ComentarioReceitaService;
import com.serasa.erestrito.service.ReceitaService;

import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequestMapping("api/v1/comentario-receita")
public class ComentarioReceitaController {

    @Autowired
    ComentarioReceitaService service;

    @Autowired
    ReceitaService receitaService;

    @GetMapping("/receita/{id}")
    public ResponseEntity<Object> getAll(
            @PathVariable Long id,
            @PageableDefault(page = 0, size = 10) @SortDefaults({
                    @SortDefault(sort = "id", direction = Direction.ASC)
            }) Pageable paginacao) {
        Page<ComentarioReceita> result = service.listarTodosPorReceita(id, paginacao);

        if (result == null) {
            return ResponseEntity.notFound().build();
        }
        // Aqui listo todos os comentarios associado aquele produto(id)

        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        Optional<ComentarioReceita> comentarioR = service.listarPorId(id);

        if (comentarioR.isPresent()) {
            return ResponseEntity.ok(comentarioR.get());
        }

        return ResponseEntity.notFound().build();
        // Aqui retorno um comentario passando seu id.
    }

    @PostMapping
    @Transactional
    public ResponseEntity<?> salvar(@RequestBody @Valid ComentarioReceitaDto payload, UriComponentsBuilder uriBuilder,
            @ApiIgnore @CurrentUser Usuario usuarioLogado) {
        Optional<Receita> receita = receitaService.listarPorId(payload.getReceita());

        if (receita.isPresent()) {
            ComentarioReceita comentarioR = new ComentarioReceita();
            comentarioR.setReceita(receita.get());
            comentarioR.setDescricao(payload.getDescricao());
            comentarioR.setUsuario(usuarioLogado);

            ComentarioReceita response = service.salvar(comentarioR);
            URI uri = uriBuilder.path("/comentario-receita/{id}").buildAndExpand(response.getId()).toUri();

            return ResponseEntity.created(uri).body(response);
        }

        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<?> atualizar(@PathVariable Long id, @RequestBody @Valid ComentarioReceitaDto form) {
        Optional<ComentarioReceita> comentarioR = service.listarPorId(id);

        if (comentarioR.isPresent()) {
            comentarioR.get().setDescricao(form.getDescricao());
            service.salvar(comentarioR.get());

            return ResponseEntity.ok(service.salvar(comentarioR.get()));
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> deletar(@PathVariable Long id) {
        Optional<ComentarioReceita> comentarioR = service.listarPorId(id);

        if (comentarioR.isPresent()) {
            service.apagar(comentarioR.get());
            return ResponseEntity.ok(comentarioR);
        }

        return ResponseEntity.notFound().build();
    }

}
