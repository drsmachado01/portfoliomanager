package br.com.darlan.portfoliomanager.util.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import br.com.darlan.portfoliomanager.dto.PessoaDTO;
import br.com.darlan.portfoliomanager.model.Pessoa;
import br.com.darlan.portfoliomanager.util.BusinessMapper;
import br.com.darlan.portfoliomanager.util.ConverterUtil;

@Component
public class PessoaMapper implements BusinessMapper<Pessoa, PessoaDTO> {

	@Override
	public PessoaDTO entityToDTO(Pessoa e) {
		return PessoaDTO.builder()
				.nome(e.getNome())
				.cpf(e.getCpf())
				.dataNascimento(ConverterUtil.asDate(e.getDataNascimento()))
				.funcionario(e.getFuncionario())
				.gerente(e.getGerente())
				.build();
	}

	@Override
	public Pessoa dtoToEntity(PessoaDTO d) {
		return Pessoa.builder()
				.nome(d.getNome())
				.cpf(d.getCpf())
				.dataNascimento(ConverterUtil.asLocalDate(d.getDataNascimento()))
				.funcionario(d.getFuncionario())
				.gerente(d.getGerente())
				.build();
	}

	@Override
	public List<PessoaDTO> listEntityToListDTO(List<Pessoa> listE) {
		return listE.stream().map(this::entityToDTO).toList();
	}

	@Override
	public List<Pessoa> listDTOToListEntity(List<PessoaDTO> listD) {
		return listD.stream().map(this::dtoToEntity).toList();
	}

}
