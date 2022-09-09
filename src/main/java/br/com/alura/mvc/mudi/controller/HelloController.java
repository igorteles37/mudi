package br.com.alura.mvc.mudi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/mudi")
public class HelloController {

	@GetMapping(value = "/hello")
	public String hello(Model model) {
		model.addAttribute("nome", "Mundo");
		return "hello";
	}
	
	@GetMapping
	public String hello2(Model model) {
		model.addAttribute("nome", "Mundo");
		return "hello";
	}
}
