package br.com.darlan.portfoliomanager.controller;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.darlan.portfoliomanager.dto.PessoaDTO;
import br.com.darlan.portfoliomanager.dto.ProjetoDTO;
import br.com.darlan.portfoliomanager.model.StatusProjeto;
import br.com.darlan.portfoliomanager.service.PessoaService;
import br.com.darlan.portfoliomanager.service.ProjetoService;
import br.com.darlan.portfoliomanager.service.exception.ExclusionNotAllowedException;
import br.com.darlan.portfoliomanager.service.exception.NotFoundException;
import br.com.darlan.portfoliomanager.util.ConverterUtil;
import ch.qos.logback.core.util.StatusPrinter;

@Controller
public class ProjetoController {
	@Autowired
	private ProjetoService projetoService;

	@Autowired
	private PessoaService pessoaService;
	
	@GetMapping("/listar-projetos")
	public String listarProjetos(Model model) {
		List<ProjetoDTO> projetos = new ArrayList<>();
		projetos.add(ProjetoDTO.builder()
				.idProjeto(1L)
				.nome("Projeto 1")
				.gerente(PessoaDTO.builder()
				.nome("Astolpho")
				.funcionario(false)
				.gerente(true)
				.build())
				.dataInicio(ConverterUtil.asDate(LocalDate.now()))
				.dataPrevisaoFim(ConverterUtil.asDate(LocalDate.now().plusMonths(6)))
				.dataFim(ConverterUtil.asDate(LocalDate.now().plusMonths(5)))
				.orcamento(new BigDecimal("200000"))
				.status(StatusProjeto.EM_ANALISE.name())
				.build());
		
		model.addAttribute("projetos", projetos);
		return "listar-projetos";
	}
	
	@GetMapping("/excluir-projeto")
	public String exibeExcluirProjeto(@RequestParam long id, ModelMap model) {
		ProjetoDTO projetoDTO = null;
		try {
			projetoDTO = projetoService.recuperarProjetoParaExclusao(id);
		} catch (ExclusionNotAllowedException | NotFoundException e) {
			e.printStackTrace();
		}
		model.addAttribute("projeto", projetoDTO);
		return "excluir-projeto";
	}
}
