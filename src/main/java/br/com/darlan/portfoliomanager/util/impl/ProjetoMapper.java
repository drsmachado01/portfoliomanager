package br.com.darlan.portfoliomanager.util.impl;

import java.util.List;

import br.com.darlan.portfoliomanager.model.StatusProjeto;
import br.com.darlan.portfoliomanager.util.ConverterUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.darlan.portfoliomanager.dto.ProjetoDTO;
import br.com.darlan.portfoliomanager.model.Projeto;
import br.com.darlan.portfoliomanager.util.BusinessMapper;

@Component
public class ProjetoMapper implements BusinessMapper<Projeto, ProjetoDTO> {

	@Autowired
	private PessoaMapper pessoaMapper;

	@Override
	public ProjetoDTO entityToDTO(Projeto e) {
		return ProjetoDTO.builder()
				.idProjeto(e.getIdProjeto())
				.nome(e.getNome())
				.descricao(e.getDescricao())
				.dataInicio(ConverterUtil.asDate(e.getDataInicio()))
				.dataPrevisaoFim(ConverterUtil.asDate(e.getDataPrevisaoFim()))
				.dataFim(ConverterUtil.asDate(e.getDataFim()))
				.orcamento(e.getOrcamento())
				.status(e.getStatus().getStatus())
				.gerente(pessoaMapper.entityToDTO(e.getGerente()))
				.risco(e.getRisco())
				.build();
	}

	@Override
	public Projeto dtoToEntity(ProjetoDTO d) {
		return Projeto.builder()
				.idProjeto(d.getIdProjeto())
				.nome(d.getNome())
				.descricao(d.getDescricao())
				.dataInicio(ConverterUtil.asLocalDate(d.getDataInicio()))
				.dataPrevisaoFim(ConverterUtil.asLocalDate(d.getDataPrevisaoFim()))
				.dataFim(ConverterUtil.asLocalDate(d.getDataFim()))
				.gerente(pessoaMapper.dtoToEntity(d.getGerente()))
				.orcamento(d.getOrcamento())
				.risco(d.getRisco())
				.status(StatusProjeto.fromString(d.getStatus()))
				.build();
	}

	@Override
	public List<ProjetoDTO> listEntityToListDTO(List<Projeto> listE) {
		return listE.stream().map(this::entityToDTO).toList();
	}

	@Override
	public List<Projeto> listDTOToListEntity(List<ProjetoDTO> listD) {
		return listD.stream().map(this::dtoToEntity).toList();
	}

}
