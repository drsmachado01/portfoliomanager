package br.com.darlan.portfoliomanager.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PortfolioManagerController {
	@GetMapping("/")
	public String index(ModelMap model) {
		model.addAttribute("nome", getLoggedInUserName(model));
		return "welcome";
	}
	
	@GetMapping("/adicionar-projeto")
	public String adicionarProjeto(ModelMap model) {
		return "adicionar-projeto";
	}
	
	private String getLoggedInUserName(ModelMap model) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof UserDetails) {
			return ((UserDetails) principal).getUsername();
		}
		return principal.toString();
	}
}
