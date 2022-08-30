package br.com.alura.mvc.mudi.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

	
	@Value("${spring.datasource.url}")
	private String bandoDeDados;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	@ResponseBody
	public String hello() {
		
		return "Utilizando Bando de dados: " + bandoDeDados;
	}
}
