package br.com.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.beans.Cliente;
import br.com.repository.IClienteRepository;

@Service
public class ClienteServiceImpl implements IClienteService {

	@Autowired
	private IClienteRepository clienteRepository;

	private Logger logger = LoggerFactory.getLogger(ClienteServiceImpl.class);

	@Override
	public Cliente insertCliente(Cliente cliente) {

		logger.info(".:: CADASTRO DO CLIENTE ::.");
		if (cliente == null) {
			throw new IllegalArgumentException("A Transportadora não pode ser nulo");
			
		}

		logger.info("[ CAD-CLIENTE ] - Iniciando persistência...");
		Cliente clienteInsert = clienteRepository.save(cliente);
		if (clienteInsert != null) 
			logger.info("[ CAD-USUARIO ] - persistência efetuada com sucesso...");
			return clienteInsert;
		
	}

	@Override
	public Cliente updateCliente(Cliente cliente) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Cliente findCliente(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Cliente> listaCliente() {
		List<Cliente>listaTodos = clienteRepository.findAll();
		if(listaTodos.isEmpty()) {
			throw new IllegalArgumentException("Não Clientes cadastrados!");
		}
		return listaTodos;
	}

	@Override
	public Cliente removeCliente(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

}
