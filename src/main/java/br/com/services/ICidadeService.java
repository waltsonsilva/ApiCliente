package br.com.services;

import java.util.List;

import br.com.beans.Cidade;

public interface ICidadeService {

	Cidade insertCidade(Cidade cidade);
	Cidade searchCidadeNome(String nome);
	List<Cidade> searchCidadeEstado(String estado);
}
