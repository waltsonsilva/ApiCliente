package br.com.services.Impl;

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
import br.com.services.IClienteService;

@Service
public class ClienteServiceImpl implements IClienteService {

	@Autowired
	private IClienteRepository clienteRepository;

	private Logger logger = LoggerFactory.getLogger(ClienteServiceImpl.class);

	@Override
	public Cliente insertCliente(Cliente cliente) {

		logger.info(".:: CADASTRO DO CLIENTE ::.");
		if (Objects.isNull(cliente)) {
			throw new IllegalArgumentException("A Cliente não pode ser nulo");

		}

		logger.info("[ CAD-CLIENTE ] - Iniciando persistência...");
		Cliente clienteInsert = clienteRepository.save(cliente);
		if (Objects.nonNull(clienteInsert))
			logger.info("[ CAD-CLIENTE ] - persistência efetuada com sucesso...");
		return clienteInsert;

	}

	
	public Cliente updateCliente(ClienteRequestDTO clienteRequest, Long id) {
		logger.info(".:: UPDATE DO CLIENTE ::.");
		Cliente  cliente = findCliente(id);
		
		if (Objects.isNull(cliente)) {
			return null;
		}
		
		cliente.setNomeCompleto(clienteRequest.getNomeCompleto());
		clienteRepository.save(cliente);
		logger.info("[ UPD-CLIENTE ] - persistência efetuada com sucesso...");
		return cliente;
	}
	
	public Cliente atualizarCliente(Long id, String nome) {
		logger.info(".:: UPDATE DO CLIENTE ::.");
		if(Objects.nonNull(id)) {
			Cliente cliente = findCliente(id);
			if(Objects.nonNull(nome) && !nome.isEmpty()) {
				cliente.setNomeCompleto(nome);
				clienteRepository.save(cliente);
				logger.info("[ UPD-CLIENTE ] - persistência efetuada com sucesso...");
			}
			
		}
		return null;
	}

	@Override
	public Cliente findCliente(Long id) {

		logger.info(".:: BUSCA DE CLIENTE ::.");
		logger.info("[ BUSCA-CLIENTE ] - Iniciando busca...");
		if (Objects.nonNull(id) &&  id <= 0) {
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


	@Override
	public Cliente searchClienteNome(String nome) {
		if(Objects.isNull(nome) && nome.isEmpty()) {
			throw new IllegalArgumentException("Nome está nulo");
		}
		Cliente cliente = clienteRepository.findByNomeCompleto(nome);
		if(Objects.isNull(cliente)) {
			throw new IllegalArgumentException("Cliente não encontrado");
		}
		return cliente;
	}



}
