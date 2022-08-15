package com.serasa.erestrito.controller;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.data.web.SortDefault.SortDefaults;

import com.serasa.erestrito.domain.dto.ProdutoDto;
import com.serasa.erestrito.domain.entity.ComentarioProduto;
import com.serasa.erestrito.domain.entity.Produto;
import com.serasa.erestrito.domain.entity.Usuario;
import com.serasa.erestrito.domain.enums.Restricao;
import com.serasa.erestrito.security.jwt.CurrentUser;
import com.serasa.erestrito.service.FileStorageService;
import com.serasa.erestrito.service.ProdutoService;

import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequestMapping("api/v1/produto")
public class ProdutoController {

  @Autowired
  ProdutoService service;

  @Autowired
  private FileStorageService fileStorageService;

  @Value("${file.upload-dir}")
  private String FILE_PATH_ROOT;

  @GetMapping
  public Page<Produto> getAll(
      @PageableDefault(page = 0, size = 10) @SortDefaults({
          @SortDefault(sort = "id", direction = Direction.ASC)
      }) Pageable paginacao,
      @RequestParam(required = false) Restricao restricao) {
    if (restricao != null) {
      return service.listarTodos(restricao, paginacao);
    } else {
      return service.listarTodos(paginacao);
    }
  }

  @GetMapping("/usuario")
  public Page<Produto> getAllByUsuario(
      @PageableDefault(page = 0, size = 10) @SortDefaults({
          @SortDefault(sort = "id", direction = Direction.ASC)
      }) Pageable paginacao,
      @ApiIgnore @CurrentUser Usuario usuarioLogado) {

    return service.listarProdutosDoUsuario(usuarioLogado, paginacao);
  }

  @GetMapping("/{id}")
  public ResponseEntity<?> getById(@PathVariable Long id) {
    Optional<Produto> produto = service.listarPorId(id);

    if (produto.isPresent()) {
      return ResponseEntity.ok(produto.get());
    }

    return ResponseEntity.notFound().build();
  }

  @PostMapping
  @Transactional
  public ResponseEntity<?> salvar(@ModelAttribute @Valid ProdutoDto payload, @RequestPart MultipartFile imagem,
      UriComponentsBuilder uriBuilder, @ApiIgnore @CurrentUser Usuario usuarioLogado) {
    String fileName = fileStorageService.storeFile(imagem);

    Produto produto = payload.converte();

    String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
        .path("api/v1/produto/preview/")
        .path(fileName)
        .toUriString();

    produto.setFoto(fileDownloadUri);
    produto.setUsuario(usuarioLogado);

    service.salvar(produto);

    URI uri = uriBuilder.path("api/v1/produto/{id}").buildAndExpand(produto.getId()).toUri();

    ProdutoDto produtoDto = new ProdutoDto();

    produtoDto.setNome(produto.getNome());
    produtoDto.setDescricao(produto.getDescricao());
    produtoDto.setMarca(produto.getMarca());
    produtoDto.setOrigem(produto.getOrigem());
    produtoDto.setTipoProduto(produto.getTipoProduto());
    produtoDto.setRestricao(produto.getRestricao());

    return ResponseEntity.created(uri).body(produtoDto);
  }

  @PutMapping("/{id}")
  @Transactional
  public ResponseEntity<?> atualizar(@PathVariable Long id, @ModelAttribute @Valid ProdutoDto payload,
      @RequestPart(required = false) MultipartFile imagem,
      UriComponentsBuilder uriBuilder, @CurrentUser Usuario usuarioLogado) {
    Optional<Produto> produto = service.listarPorId(id);

    if (produto.isPresent()) {
      Produto prod = payload.converte();
      prod.setFoto(produto.get().getFoto());
      prod.setId(id);
      prod.setUsuario(usuarioLogado);

      if (imagem != null) {
        String fileName = fileStorageService.storeFile(imagem);

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
            .path("api/v1/produto/preview/")
            .path(fileName)
            .toUriString();

        prod.setFoto(fileDownloadUri);
      }

      return ResponseEntity.ok(service.salvar(prod));
    }

    return ResponseEntity.notFound().build();
  }

  @GetMapping("/downloadFile/{fileName:.+}")
  public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request) {
    Resource resource = fileStorageService.loadFileAsResource(fileName);

    String contentType = null;

    try {
      contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
    } catch (Exception e) {
      System.out.println("Could not determine file type!");
    }

    if (contentType == null) {
      contentType = "application/octet-stream";
    }

    return ResponseEntity.ok()
        .contentType(MediaType.parseMediaType(contentType))
        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
        .body(resource);

  }

  @GetMapping(value = "/preview/{fileName:.+}")
  public ResponseEntity<byte[]> previewFile(@PathVariable String fileName, HttpServletRequest request)
      throws IOException {
    byte[] image = new byte[0];
    try {
      image = FileUtils.readFileToByteArray(new File(FILE_PATH_ROOT + fileName));
    } catch (IOException e) {
      e.printStackTrace();
    }
    return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(image);
  }

  @DeleteMapping("/{id}")
  @Transactional
  public ResponseEntity<?> deletar(@PathVariable Long id) {
    Optional<Produto> produto = service.listarPorId(id);

    if (produto.isPresent()) {
      service.apagar(produto.get());
      return ResponseEntity.ok(produto);
    }

    return ResponseEntity.notFound().build();
  }

}
