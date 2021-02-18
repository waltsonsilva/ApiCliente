package br.com.services;

import java.util.List;

import br.com.beans.Cliente;
import br.com.dto.ClienteRequestDTO;

public interface IClienteService {

	Cliente insertCliente(Cliente cliente);
	Cliente updateCliente(ClienteRequestDTO cliente, Long id);
	Cliente findCliente(Long id);
	List<Cliente> listaCliente();
	Cliente removeCliente (Long id);
	Cliente atualizarCliente(Long id, String nome);
	Cliente searchClienteNome(String nome);
}
