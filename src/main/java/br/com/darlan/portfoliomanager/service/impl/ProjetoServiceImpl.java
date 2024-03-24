package br.com.darlan.portfoliomanager.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.darlan.portfoliomanager.dto.PessoaDTO;
import br.com.darlan.portfoliomanager.dto.ProjetoDTO;
import br.com.darlan.portfoliomanager.model.Pessoa;
import br.com.darlan.portfoliomanager.model.Projeto;
import br.com.darlan.portfoliomanager.model.StatusProjeto;
import br.com.darlan.portfoliomanager.repository.PessoaRepository;
import br.com.darlan.portfoliomanager.repository.ProjetoRepository;
import br.com.darlan.portfoliomanager.service.ProjetoService;
import br.com.darlan.portfoliomanager.service.exception.ExclusionNotAllowedException;
import br.com.darlan.portfoliomanager.service.exception.NotFoundException;
import br.com.darlan.portfoliomanager.util.BusinessMapper;

@Service
public class ProjetoServiceImpl implements ProjetoService {

	@Autowired
	private ProjetoRepository repo;
	
	@Autowired
	private PessoaRepository pessoaRepo;
	
	@Autowired
	private BusinessMapper<Projeto, ProjetoDTO> projetoMapper;
	
	@Autowired
	private BusinessMapper<Pessoa, PessoaDTO> pessoaMapper;

	@Override
	public ProjetoDTO persistir(ProjetoDTO dto) {
		return projetoMapper.entityToDTO(repo.save(projetoMapper.dtoToEntity(dto)));
	}

	@Override
	public List<ProjetoDTO> listar() {
		return projetoMapper.listEntityToListDTO(repo.findAll());
	}

	@Override
	public ProjetoDTO pesquisarPorId(Long id) throws NotFoundException {
		return projetoMapper.entityToDTO(repo.findById(id).orElseThrow(() -> new NotFoundException("Projeto náo encontrado!")));
	}

	@Override
	public ProjetoDTO atualizar(Long id, ProjetoDTO d) {
		return null;
	}

	@Override
	public void excluir(Long id) throws NotFoundException {
		pesquisarPorId(id);
		repo.deleteById(id);
	}

	@Override
	public List<ProjetoDTO> listarProjetosPorGerente(PessoaDTO pessoaDTO) throws NotFoundException {
		return projetoMapper.listEntityToListDTO(repo.findByGerenteIdPessoa(pessoaDTO.getIdPessoa()).orElseThrow(() -> new NotFoundException("Nenhum projeto encontrado!")));
	}

	@Override
	public ProjetoDTO recuperarProjetoParaExclusao(Long idPessoa) throws ExclusionNotAllowedException, NotFoundException {
		ProjetoDTO projetoDTO = pesquisarPorId(idPessoa);
		if(validarStatusParaExcluir(projetoDTO.getStatus())) {
			return projetoDTO;
		}
		throw new ExclusionNotAllowedException("Projeto em status que impede a exclusão!");
	}

	private boolean validarStatusParaExcluir(String status) {
		for(StatusProjeto sp : StatusProjeto.values()) {
			if(sp.name().equals(status)) {
				return false;
			}
		}
		return true;
	}
}
