package br.com.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClienteResponseDTO implements Serializable {

	private Long id;
	private String nomeCompleto;
	private String dataNascimento;
	private int idade;
}
