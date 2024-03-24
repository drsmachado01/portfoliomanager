package br.com.darlan.portfoliomanager.util.impl;

import br.com.darlan.portfoliomanager.dto.ProjetoDTO;
import br.com.darlan.portfoliomanager.model.Projeto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static br.com.darlan.portfoliomanager.util.TesteUtil.buildProjeto;
import static br.com.darlan.portfoliomanager.util.TesteUtil.buildProjetoDTO;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProjetoMapperTest {

    @Autowired
    private ProjetoMapper mapper;

    @Test
    void entityToDTO() {
        ProjetoDTO dto = mapper.entityToDTO(buildProjeto("Iniciado", "ALTO"));
        assertNotNull(dto);
        assertEquals(buildProjetoDTO("Iniciado", "ALTO"), dto);
    }

    @Test
    void dtoToEntity() {
        Projeto e = mapper.dtoToEntity(buildProjetoDTO("Iniciado", "ALTO"));
        assertNotNull(e);
        assertEquals(buildProjeto("Iniciado", "ALTO"), e);
    }

    @Test
    void listEntityToListDTO() {
        List<ProjetoDTO> listDTO = mapper.listEntityToListDTO(List.of(buildProjeto("Iniciado", "ALTO")));
        assertNotNull(listDTO);
        assertEquals(1, listDTO.size());
        assertEquals(buildProjetoDTO("Iniciado", "ALTO"), listDTO.get(0));
    }

    @Test
    void listDTOToListEntity() {
        List<Projeto> listEntity = mapper.listDTOToListEntity(List.of(buildProjetoDTO("Iniciado", "ALTO")));
        assertNotNull(listEntity);
        assertEquals(1, listEntity.size());
        assertEquals(buildProjeto("Iniciado", "ALTO"), listEntity.get(0));
    }

}