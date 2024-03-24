package br.com.darlan.portfoliomanager.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.darlan.portfoliomanager.dto.PessoaDTO;
import br.com.darlan.portfoliomanager.model.Pessoa;
import br.com.darlan.portfoliomanager.repository.PessoaRepository;
import br.com.darlan.portfoliomanager.service.PessoaService;
import br.com.darlan.portfoliomanager.service.exception.NotFoundException;
import br.com.darlan.portfoliomanager.util.BusinessMapper;

@Service
public class PessoaServiceImpl implements PessoaService {
	@Autowired
	private PessoaRepository repo;
	
	@Autowired
	private BusinessMapper<Pessoa, PessoaDTO> pessoaMapper;

	@Override
	public PessoaDTO persistir(PessoaDTO d) {
		return pessoaMapper.entityToDTO(repo.save(pessoaMapper.dtoToEntity(d)));
	}

	@Override
	public List<PessoaDTO> listar() {
		return pessoaMapper.listEntityToListDTO(repo.findAll());
	}

	@Override
	public PessoaDTO pesquisarPorId(Long id) throws NotFoundException {
		return pessoaMapper.entityToDTO(repo.findById(id).orElseThrow(() -> new NotFoundException("User not found!")));
	}

	@Override
	public PessoaDTO atualizar(Long id, PessoaDTO d) {
		pesquisarPorId(id);
		d.setIdPessoa(id);
		return pessoaMapper.entityToDTO(repo.save(pessoaMapper.dtoToEntity(d)));
	}

	@Override
	public void excluir(Long id) throws NotFoundException {
		pesquisarPorId(id);
		repo.deleteById(id);
	}
}
