package br.com.darlan.portfoliomanager.service;

import br.com.darlan.portfoliomanager.dto.PessoaDTO;
import br.com.darlan.portfoliomanager.model.Pessoa;
import br.com.darlan.portfoliomanager.repository.PessoaRepository;
import br.com.darlan.portfoliomanager.service.exception.NotFoundException;
import br.com.darlan.portfoliomanager.service.impl.PessoaServiceImpl;
import br.com.darlan.portfoliomanager.util.BusinessMapper;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static br.com.darlan.portfoliomanager.util.TesteUtil.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
class PessoaServiceTest {
    @Mock
    private PessoaRepository repo;

    @Mock
    private BusinessMapper<Pessoa, PessoaDTO> pessoaMapper;

    @InjectMocks
    private PessoaServiceImpl service;


    @Test
    void testPersistir() {
        when(pessoaMapper.dtoToEntity(any())).thenReturn(buildPessoaFuncionario());
        when(repo.save(any())).thenReturn(buildPessoaFuncionario());
        when(pessoaMapper.entityToDTO(any())).thenReturn(buildFuncionario());

        PessoaDTO pessoa = service.persistir(buildFuncionario());
        assertNotNull(pessoa);
        assertEquals(buildFuncionario(), pessoa);
    }

    @Test
    void testAlterar() {
        when(repo.findById(1L)).thenReturn(Optional.of(buildPessoaFuncionario()));
        when(pessoaMapper.dtoToEntity(any())).thenReturn(buildPessoaFuncionario());
        when(repo.save(any())).thenReturn(buildPessoaFuncionario());
        when(pessoaMapper.entityToDTO(any())).thenReturn(buildFuncionario());

        PessoaDTO pessoa = service.atualizar(1L, buildFuncionario());
        assertNotNull(pessoa);
        assertEquals(buildFuncionario(), pessoa);
    }

    @Test
    void testListar() {
        when(repo.findAll()).thenReturn(List.of(buildPessoaFuncionario(), buildPessoaGerente()));
        when(pessoaMapper.listEntityToListDTO(List.of(buildPessoaFuncionario(), buildPessoaGerente()))).thenReturn(List.of(buildFuncionario(), buildGerente()));

        List<PessoaDTO> pessoas = service.listar();
        assertNotNull(pessoas);
        assertEquals(2, pessoas.size());
        assertEquals(buildFuncionario(), pessoas.get(0));
        assertEquals(buildGerente(), pessoas.get(1));
    }

    @Test
    void testPesquisarPorId() {
        when(repo.findById(1L)).thenReturn(java.util.Optional.of(buildPessoaFuncionario()));
        when(pessoaMapper.entityToDTO(buildPessoaFuncionario())).thenReturn(buildFuncionario());

        PessoaDTO pessoa = service.pesquisarPorId(1L);
        assertNotNull(pessoa);
        assertEquals(buildFuncionario(), pessoa);
    }

    @Test
    void testExcluir() {
        when(repo.findById(1L)).thenReturn(Optional.of(buildPessoaFuncionario()));
        when(repo.save(any())).thenReturn(buildPessoaFuncionario());
        when(pessoaMapper.entityToDTO(any())).thenReturn(buildFuncionario());

        service.excluir(1L);
        verify(repo).deleteById(1L);
    }
}