package br.com.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.beans.Cliente;
import br.com.services.IClienteService;

@RestController
@RequestMapping("/cliente")
public class ClienteController {
	
	@Autowired
	private IClienteService clienteService;
	
	@PostMapping("/inserir")
	public ResponseEntity<Cliente>insertCliente (@Valid @RequestBody Cliente cliente)
	{
		if(cliente ==null) {			
			 throw new IllegalArgumentException("Não foi possível realizar a insersão");
		}
		Cliente clienteInsert = clienteService.insertCliente(cliente);
		return new ResponseEntity<Cliente>(clienteInsert, HttpStatus.CREATED);
	}
	
	@GetMapping("listarClientes")
	public ResponseEntity<List<Cliente>>listar(){
		List<Cliente> listaClientes = clienteService.listaCliente();
		if(listaClientes.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(listaClientes, HttpStatus.OK);
	
	}

}
