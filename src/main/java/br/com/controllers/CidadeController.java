package br.com.controllers;

import java.util.List;
import java.util.Objects;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.beans.Cidade;
import br.com.services.ICidadeService;

@RestController
@RequestMapping("/cidade")
public class CidadeController {

	
	@Autowired
	private ICidadeService cidadeService;
	
	@PostMapping("/inserir")
	public ResponseEntity<Cidade>inserirCidade(@Valid @RequestBody Cidade cidade){
		if(Objects.isNull(cidade)) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		Cidade cidadeInsert = cidadeService.insertCidade(cidade);
		if(Objects.isNull(cidadeInsert)) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Cidade>(cidadeInsert, HttpStatus.CREATED);
	}
	
	@GetMapping("/pesqPorNome/{nome}")
	public ResponseEntity<Cidade>pesqPorNome(@PathVariable String nome){
		if(Objects.isNull(nome)) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		Cidade cidadeSearch = cidadeService.searchCidadeNome(nome);
		if(Objects.isNull(cidadeSearch)) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Cidade>(cidadeSearch, HttpStatus.OK);
	}
	
	@GetMapping("/pesqPorEstado/{estado}")
	public ResponseEntity<List<Cidade>>pesqPorEstado(@PathVariable String estado){
		if(Objects.isNull(estado)) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		 List<Cidade> cidade = cidadeService.searchCidadeEstado(estado);
		 if(cidade.isEmpty()) {
			 return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		 }
		return new ResponseEntity<List<Cidade>>(cidade, HttpStatus.OK);
	}
	
}
