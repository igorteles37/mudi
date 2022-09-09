package br.com.alura.mvc.mudi.controller;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.alura.mvc.mudi.controller.form.PedidoForm;
import br.com.alura.mvc.mudi.model.Pedido;
import br.com.alura.mvc.mudi.repository.PedidoReposotiry;

@Controller
@RequestMapping("/pedido")
public class PedidoController {


	@Autowired
	PedidoReposotiry pedidoRepository;
	
	
	
	@GetMapping("/formulario")
	public String formulario() {
		
		return "pedido/formulario";
	}
	
	@PostMapping("/novo")
	public String novo(PedidoForm pedidoForm) {
		
		Pedido pedido  = pedidoForm.toPedido();
		pedidoRepository.save(pedido);
		
		return "pedido/formulario";
	}

}
