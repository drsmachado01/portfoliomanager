package br.com.darlan.portfoliomanager.repository;

import br.com.darlan.portfoliomanager.model.Projeto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProjetoRepositoryTest {

    @Autowired
    private ProjetoRepository repository;
}