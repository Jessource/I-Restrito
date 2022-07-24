package com.serasa.erestrito.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.serasa.erestrito.domain.enums.Restricao;

@RestController
@RequestMapping("api/v1/tipo-restricao")
public class TipoRestricaoController {

	@GetMapping
	public List<?> getAll() {
		List<HashMap<String, String>> result = new ArrayList<>();

		HashMap<String, String> map = new HashMap<>();
		map.put("descricao", "Glúten");
		map.put("id", Restricao.GLUTEN.toString());
		result.add(map);

		map = new HashMap<>();
		map.put("descricao", "Lactose");
		map.put("id", Restricao.LACTOSE.toString());
		result.add(map);

		return result;
	}
}
