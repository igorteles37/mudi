package br.com.alura.mvc.mudi.controller;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.HttpClientErrorException.MethodNotAllowed;
import org.springframework.web.servlet.ModelAndView;

import br.com.alura.mvc.mudi.controller.form.PedidoForm;
import br.com.alura.mvc.mudi.model.Pedido;
import br.com.alura.mvc.mudi.repository.PedidoReposotiry;

@Controller
@RequestMapping("pedido")
public class PedidoController {


	@Autowired
	PedidoReposotiry pedidoRepository;
	
	
	
	@GetMapping("formulario")
	public String formulario(PedidoForm pedidoForm) {
		
		return "pedido/formulario";
	}
	
	@PostMapping("novo")
	public ModelAndView novo(@Valid PedidoForm pedidoForm, BindingResult result, Model model) {
		ModelAndView mv;
		if (result.hasErrors()) {
			mv = new ModelAndView("pedido/formulario");
			return mv;
		}
			
		
		Pedido pedido  = pedidoForm.toPedido();
		pedidoRepository.save(pedido);
		
		mv = new ModelAndView("/home");
		
	    mv.addObject("mensagem", "Sucesso");
	    return mv; 
		
		//return "redirect:/home";
		//return "pedido/formulario";
	}
	
	@ExceptionHandler(MethodNotAllowed.class)
	public String onError() {
		return "redirect:/home";
	}

}
