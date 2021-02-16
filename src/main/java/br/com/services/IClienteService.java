package br.com.services;

import java.util.List;

import br.com.beans.Cliente;

public interface IClienteService {

	Cliente insertCliente(Cliente cliente);
	Cliente updateCliente(Cliente cliente);
	Cliente findCliente(Long id);
	List<Cliente> listaCliente();
	Cliente removeCliente (Long id);
}
