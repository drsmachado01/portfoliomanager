package br.com.darlan.portfoliomanager.service;

import java.util.List;

import br.com.darlan.portfoliomanager.dto.PessoaDTO;
import br.com.darlan.portfoliomanager.dto.ProjetoDTO;
import br.com.darlan.portfoliomanager.service.exception.ExclusionNotAllowedException;
import br.com.darlan.portfoliomanager.service.exception.NotFoundException;

public interface ProjetoService extends BusinessService<ProjetoDTO, Long> {
	List<ProjetoDTO> listarProjetosPorGerente(PessoaDTO pessoaDTO) throws NotFoundException;

	ProjetoDTO recuperarProjetoParaExclusao(Long idPessoa) throws ExclusionNotAllowedException, NotFoundException;
}
