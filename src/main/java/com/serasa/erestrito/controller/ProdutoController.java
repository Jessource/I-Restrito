package com.serasa.erestrito.controller;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import com.serasa.erestrito.domain.dto.ProdutoDto;
import com.serasa.erestrito.domain.entity.Produto;
import com.serasa.erestrito.domain.entity.TipoAdicao;
import com.serasa.erestrito.service.FileStorageService;
import com.serasa.erestrito.service.ProdutoService;

@RestController
@RequestMapping("/produto")
public class ProdutoController {

  @Autowired
  ProdutoService service;

  @Autowired
  private FileStorageService fileStorageService;
  
  @Value("${file.upload-dir}")
  private String FILE_PATH_ROOT;


  @GetMapping
  public List<Produto> getAll() {
    return service.listarTodos();
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
      UriComponentsBuilder uriBuilder) {
    String fileName = fileStorageService.storeFile(imagem);

    Produto produto = payload.converte();

    String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
        .path("/produto/preview/")
        .path(fileName)
        .toUriString();

    produto.setFoto(fileDownloadUri);

    service.salvar(produto);

    URI uri = uriBuilder.path("/produto/{id}").buildAndExpand(produto.getId()).toUri();

    return ResponseEntity.created(uri).body(produto);
  }
  
  @PutMapping("/{id}")
  @Transactional
  public ResponseEntity<?> atualizar(@PathVariable Long id, @ModelAttribute @Valid ProdutoDto payload, @RequestPart MultipartFile imagem,
	      UriComponentsBuilder uriBuilder) {
    Optional<Produto> produto = service.listarPorId(id);

    if (produto.isPresent()) {
    	Produto prod = payload.converte();
    	prod.setFoto(produto.get().getFoto());
    	prod.setId(id);
    	
    	if(!imagem.isEmpty()) {
    		String fileName = fileStorageService.storeFile(imagem);

    	    String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
    	        .path("/produto/preview/")
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

  @GetMapping(value = "/preview/{fileName:.+}", produces = MediaType.IMAGE_JPEG_VALUE)
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

}
