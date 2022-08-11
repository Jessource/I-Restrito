package com.serasa.erestrito.repository;

import java.time.LocalDate;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.serasa.erestrito.domain.entity.Usuario;
import com.serasa.erestrito.domain.enums.Perfil;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
public class UsuarioRepositoryTest {

	@Autowired
	private UsuarioRepository repository;
	
	@Autowired
    private TestEntityManager em;

	@Test
	public void IncluindoListandoEDeletandoUmUsuario() {
		Usuario usuario = new Usuario("Teste", "Teste", "teste@teste.com", "Senha", "RO", "Sao Paulo", LocalDate.of(2001, 1, 1),
				Perfil.ADMIN);

		String nomeUsuario = "Teste";
		em.persist(usuario);

		Iterable<Usuario> usuarios = repository.findByNome(nomeUsuario);
		Assertions.assertThat(usuarios).isNotEmpty();
		Assertions.assertThat(usuarios).extracting("nome").contains(nomeUsuario);

		repository.deleteAll();
		Assertions.assertThat(repository.findAll()).isEmpty();
	}

}
