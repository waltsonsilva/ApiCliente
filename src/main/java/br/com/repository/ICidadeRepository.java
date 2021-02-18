package br.com.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.beans.Cidade;

@Repository
public interface ICidadeRepository extends JpaRepository<Cidade, Long>{
	
	Cidade findByNomeCidade(String nome);
	List<Cidade> findByEstado(String estado);

}
