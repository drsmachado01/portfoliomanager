package br.com.darlan.portfoliomanager.dto;

import java.util.Date;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class PessoaDTO {
	private Long idPessoa;
	private String nome;
	private Date dataNascimento;
	private String cpf;
	private Boolean funcionario;
	private Boolean gerente;
}
