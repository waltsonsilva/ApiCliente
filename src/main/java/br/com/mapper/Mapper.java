package br.com.mapper;

import org.springframework.stereotype.Component;

import br.com.beans.Cliente;
import br.com.dto.ClienteRequestDTO;
import br.com.dto.ClienteResponseDTO;

@Component
public class Mapper {

	public ClienteResponseDTO toResponse(Cliente cliente) {
		return ClienteResponseDTO.builder().id(cliente.getCodigoCliente()).nomeCompleto(cliente.getNomeCompleto()).dataNascimento(cliente.getData_nascimento()).idade(cliente.getIdade()).build();
	}
	
	public Cliente toRequest(ClienteRequestDTO dto) {
		return Cliente.builder().nomeCompleto(dto.getNome()).build();
	}
}
