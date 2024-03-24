package br.com.darlan.portfoliomanager.dto;

import java.math.BigDecimal;
import java.util.Date;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ProjetoDTO {
	private Long idProjeto;
	private String nome;
	private Date dataInicio;
	private Date dataPrevisaoFim;
	private Date dataFim;
	private String descricao;
	private String status;
	private BigDecimal orcamento;
	private String risco;
	private PessoaDTO gerente;
}
