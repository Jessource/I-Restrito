package com.serasa.erestrito.repository;

import java.time.LocalDate;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.serasa.erestrito.domain.entity.Receita;
import com.serasa.erestrito.domain.entity.Usuario;
import com.serasa.erestrito.domain.enums.Perfil;
import com.serasa.erestrito.domain.enums.Restricao;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
public class ReceitaRepositoryTest {
	
	@Autowired
	private ReceitaRepository repository;
	
	@Autowired
	private TestEntityManager em;
	
	@Test
	public void IncluindoListandoEDeletandoUmaReceita() {
		Pageable paginacao = PageRequest.of(0, 10);
		
		Usuario usuario = new Usuario("Teste", "Teste", "teste@teste.com", "Senha", "RO","Sao Paulo", LocalDate.of(2001, 1, 1),
				Perfil.ADMIN);
		Receita receita = new Receita("Teste", "Teste", "Teste", "Teste", "Teste", "Teste",
				Restricao.LACTOSE, usuario);
		
		em.persist(usuario);
		em.persist(receita);

		Iterable<Receita> receitas = repository.findByRestricao(Restricao.LACTOSE, paginacao );
		Assertions.assertThat(receitas).isNotEmpty();
		Assertions.assertThat(receitas).extracting("titulo").contains("Teste");

		repository.deleteAll();
		Assertions.assertThat(repository.findAll()).isEmpty();
	}

}
