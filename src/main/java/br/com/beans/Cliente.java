package br.com.beans;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="cliente")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Cliente implements Serializable{

	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigoCliente;
	
	@NotBlank
	private String nomeCompleto;
	
	@Column(nullable = true)
	private String Sexo;
	
	private int idade;
	
	private String data_nascimento; 

}
