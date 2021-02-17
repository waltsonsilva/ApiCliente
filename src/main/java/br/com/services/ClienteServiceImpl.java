package br.com.services;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.beans.Cliente;
import br.com.dto.ClienteRequestDTO;
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
			logger.info("[ CAD-CLIENTE ] - persistência efetuada com sucesso...");
		return clienteInsert;

	}

	
	public Cliente updateCliente(ClienteRequestDTO clienteRequest, Long id) {
		Cliente  cliente = findCliente(id);
		if (cliente == null) {
			return null;
		}
		cliente.setNomeCompleto(clienteRequest.getNomeCompleto());
		return clienteRepository.save(cliente);

	}

	@Override
	public Cliente findCliente(Long id) {

		logger.info(".:: BUSCA DE CLIENTE ::.");
		logger.info("[ BUSCA-CLIENTE ] - Iniciando busca...");
		if (id == null && id <= 0) {
			logger.info("[ BUSCA-CLIENTEl ] -  Usuario não pode ter código nulo ou menor/igual a zero");
		}
		Optional<Cliente> cliente = clienteRepository.findById(id);
		return cliente.orElseThrow(()-> new IllegalArgumentException("O cliente do código " + id + " não foi encontrado!"));
	}

	@Override
	public List<Cliente> listaCliente() {
		List<Cliente> listaTodos = clienteRepository.findAll();
		if (listaTodos.isEmpty()) {
			throw new IllegalArgumentException("Não Clientes cadastrados!");
		}
		return listaTodos;
	}

	@Override
	public Cliente removeCliente(Long id) {
		if(Objects.nonNull(id)) {
			Cliente cliente = findCliente(id);
			clienteRepository.delete(cliente);
		}
		return null;
	}



}
