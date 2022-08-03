package com.serasa.erestrito.controller;

import java.net.URI;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.serasa.erestrito.service.ProdutoService;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class ProdutoControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@Test
	public void testeFindAll() throws Exception {
		
		URI uri = new URI("/api/v1/produto");

		mockMvc.perform(MockMvcRequestBuilders
				.get(uri))
				.andExpect(MockMvcResultMatchers
				.status()
				.is(200));
	}
	
	@Test
	public void testeDeleteSemLogin() throws Exception {
		
		URI uri = new URI("/api/v1/produto/1");

		mockMvc.perform(MockMvcRequestBuilders
				.delete(uri))
				.andExpect(MockMvcResultMatchers
				.status()
				.is(401));
	}

}
