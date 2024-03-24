package br.com.darlan.portfoliomanager.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "projeto")
@Data
public class Projeto {
	@Column(name = "id")
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PROJETO_ID_SEQ")
	@SequenceGenerator(name = "PROJETO_ID_SEQ", sequenceName = "PROJETO_ID_SEQ", allocationSize = 1)
	private Long idProjeto;
	@Column(nullable = false, length = 200)
	private String nome;
	@Column(name = "data_inicio")
	private LocalDate dataInicio;
	@Column(name = "data_previsao_fim")
	private LocalDate dataPrevisaoFim;
	@Column(name = "data_fim")
	private LocalDate dataFim;
	@Column(length = 5000)
	private String descricao;
	@Enumerated
	@Column(length = 45)
	private StatusProjeto status;
	private BigDecimal orcamento;
	@Column(length = 45)
	private String risco;
	@ManyToOne
	private Pessoa gerente;

}
