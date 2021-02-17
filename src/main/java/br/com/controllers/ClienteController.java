package br.com.controllers;

import java.util.List;
import java.util.Objects;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.beans.Cliente;
import br.com.dto.ClienteRequestDTO;
import br.com.dto.ClienteResponseDTO;
import br.com.mapper.Mapper;
import br.com.services.IClienteService;

@RestController
@RequestMapping("/cliente")
public class ClienteController {
	
	private IClienteService clienteService;
	private Mapper mapper;
	
	@Autowired
	public ClienteController(IClienteService clienteService, Mapper mapper) {
		this.clienteService = clienteService;
		this.mapper = mapper;
	}
	
	
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
	
	@GetMapping("/buscarId/{id}")
	public ResponseEntity<Cliente>buscarPorId(@PathVariable Long id){
		Cliente cliente = clienteService.findCliente(id);
		if(cliente == null) {
			return new ResponseEntity<Cliente>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Cliente>(cliente, HttpStatus.OK);
	}
	
	
	@PutMapping("/atualiar/{id}")
	public ResponseEntity<ClienteResponseDTO>atualizarCliente(@PathVariable Long id, @RequestBody ClienteRequestDTO cliente){
		if(cliente !=null) {
			Cliente clienteUpdate = clienteService.updateCliente(cliente, id);
			ClienteResponseDTO clienteResponse = mapper.toResponse(clienteUpdate);
			return new ResponseEntity<ClienteResponseDTO>(clienteResponse, HttpStatus.OK);
		}
		return null;
	}

	@DeleteMapping("/deletar/{id}")
	public ResponseEntity<Cliente>deletarCliente(@PathVariable Long id){
		if(Objects.nonNull(id)) {
			clienteService.removeCliente(id);
			return new ResponseEntity<>(HttpStatus.OK);
		}
		return null;
	}
}
