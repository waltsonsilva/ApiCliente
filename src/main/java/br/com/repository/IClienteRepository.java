package br.com.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.beans.Cliente;

@Repository
public interface IClienteRepository extends JpaRepository<Cliente, Long> {
	
	Cliente findByNomeCompleto(String nome);

}
