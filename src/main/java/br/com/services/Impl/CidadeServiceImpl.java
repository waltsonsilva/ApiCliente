package br.com.services.Impl;

import java.util.List;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.beans.Cidade;
import br.com.repository.ICidadeRepository;
import br.com.services.ICidadeService;

@Service
public class CidadeServiceImpl implements ICidadeService{

	
	@Autowired
	private ICidadeRepository cidadeRepositori;
	private Logger logger = LoggerFactory.getLogger(CidadeServiceImpl.class);
	
	@Override
	public Cidade insertCidade(Cidade cidade) {
		logger.info(".:: CADASTRO DO CIDADE ::.");
		if(Objects.isNull(cidade)) {
			throw new IllegalArgumentException("Não é possível salvar uma cidade null");
			
		}
		logger.info("[ CAD-CIDADE ] - Iniciando persistência...");
		Cidade cidadeInsert = cidadeRepositori.save(cidade);
		if(Objects.isNull(cidadeInsert)) {
			throw new IllegalArgumentException("Não foi possível cadastrar cidade");
		}
		logger.info("[ CAD-CLIENTE ] - persistência efetuada com sucesso...");
		return cidadeInsert;
	}

	@Override
	public Cidade searchCidadeNome(String nome) {
		if(Objects.isNull(nome) && nome.isEmpty()) {
			throw new IllegalArgumentException("O filtro Nome não está preenchido");
		}
		Cidade cidadeSearch = cidadeRepositori.findByNomeCidade(nome);
		if(Objects.isNull(cidadeSearch)) {
			throw new IllegalArgumentException("Cidade(s) não encontrada");
		}
		return cidadeSearch;
	}

	@Override
	public List<Cidade> searchCidadeEstado(String estado) {
		if(Objects.isNull(estado)) {
			throw new IllegalArgumentException("Estado está nulo");
		}
		 List<Cidade>cidades = cidadeRepositori.findByEstado(estado);
		if(Objects.isNull(cidades) && cidades.isEmpty()) {
			throw new IllegalArgumentException("Cidade(s) não encontrada");
		}
		return cidades;
	}

}
