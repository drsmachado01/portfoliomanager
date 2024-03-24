package br.com.darlan.portfoliomanager.util.impl;

import br.com.darlan.portfoliomanager.dto.PessoaDTO;
import br.com.darlan.portfoliomanager.model.Pessoa;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static br.com.darlan.portfoliomanager.util.TesteUtil.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PessoaMapperTest {

    @Autowired
    PessoaMapper mapper;

    @Test
    void entityToDTO() {
        PessoaDTO dto = mapper.entityToDTO(buildPessoaFuncionario());
        assertNotNull(dto);
        assertEquals(buildFuncionario(), dto);
        assertTrue(dto.getFuncionario());
    }

    @Test
    void dtoToEntity() {
        Pessoa e = mapper.dtoToEntity(buildFuncionario());
        assertNotNull(e);
        assertEquals(buildPessoaFuncionario(), e);
        assertTrue(e.getFuncionario());
    }

    @Test
    void listEntityToListDTO() {
        List<PessoaDTO> dtos = mapper.listEntityToListDTO(List.of(buildPessoaFuncionario(), buildPessoaGerente()));
        assertNotNull(dtos);
        assertEquals(2, dtos.size());
        assertEquals(buildFuncionario(), dtos.get(0));
        assertEquals(buildGerente(), dtos.get(1));
        assertTrue(dtos.get(0).getFuncionario());
        assertFalse(dtos.get(1).getFuncionario());
    }

    @Test
    void listDTOToListEntity() {
        List<Pessoa> entities = mapper.listDTOToListEntity(List.of(buildFuncionario(), buildGerente()));
        assertNotNull(entities);
        assertEquals(2, entities.size());
        assertEquals(buildPessoaFuncionario(), entities.get(0));
        assertEquals(buildPessoaGerente(), entities.get(1));
        assertTrue(entities.get(0).getFuncionario());
        assertFalse(entities.get(1).getFuncionario());
    }
}