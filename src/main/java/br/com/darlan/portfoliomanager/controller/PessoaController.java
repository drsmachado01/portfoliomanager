package br.com.darlan.portfoliomanager.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import br.com.darlan.portfoliomanager.service.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import br.com.darlan.portfoliomanager.dto.PessoaDTO;
import br.com.darlan.portfoliomanager.service.PessoaService;

@Controller
public class PessoaController {

	@Autowired
	private PessoaService pessoaService;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	}
	
	@GetMapping("/adicionar-pessoa")
	public String adicionarPessoa(ModelMap model) {
		model.addAttribute("pessoa", PessoaDTO.builder().build());
		return "adicionar-pessoa";
	}

	@GetMapping("/listar-pessoas")
	public String listarPessoas(Model model) {
		List<PessoaDTO> pessoas = pessoaService.listar();
		model.addAttribute("pessoas", pessoas);
		return "listar-pessoas";
	}

	@PostMapping("/adicionar-pessoa")
	public String adicionarPessoa(@Valid PessoaDTO pessoa, BindingResult result, ModelMap model) {
		if (result.hasErrors()) {
			return "";
		}
		
		if(!Boolean.TRUE.equals(pessoa.getGerente())) {
			pessoa.setFuncionario(Boolean.TRUE);
		}
		
		pessoaService.persistir(pessoa);
		return "redirect:/listar-pessoas";
	}

	@GetMapping("/buscarPessoaPorId")
	public String buscarPessoaPorId(@RequestParam Long id, ModelMap model) {
		PessoaDTO pessoa = pessoaService.pesquisarPorId(id);
		model.addAttribute("pessoa", pessoa);
		return "exibir-pessoa";
	}

	@GetMapping("/atualizar-pessoa")
	public String atualizarPessoa(@RequestParam Long id, ModelMap model) {
		PessoaDTO pessoa = pessoaService.pesquisarPorId(id);
		model.addAttribute("pessoa", pessoa);
		return "atualizar-pessoa";
	}

	@PutMapping("/atualizar-pessoa")
	public String atualizarPessoa(@RequestParam Long id, @Valid PessoaDTO pessoa, BindingResult result, ModelMap model) {
		if (result.hasErrors()) {
			return "";
		}

		pessoaService.atualizar(id, pessoa);
		return "redirect:/listar-pessoas";
	}

	@GetMapping("/excluir-pessoa")
	public String preparaExcluirPessoa(@RequestParam Long id, ModelMap model) {
		PessoaDTO pessoaDTO = pessoaService.pesquisarPorId(id);
		model.addAttribute("pessoa", pessoaDTO);
		return "redirect:/listar-pessoas";
	}

	@DeleteMapping("/excluir-pessoa")
	public String excluirPessoa(@RequestParam Long id, ModelMap model) {
		pessoaService.excluir(id);
		return "redirect:/listar-pessoas";
	}
}
