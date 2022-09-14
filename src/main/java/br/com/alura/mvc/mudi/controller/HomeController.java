package br.com.alura.mvc.mudi.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.alura.mvc.mudi.model.Pedido;
import br.com.alura.mvc.mudi.model.StatusPedido;
import br.com.alura.mvc.mudi.repository.PedidoReposotiry;

@Controller
@RequestMapping("/home")
public class HomeController {
	
	@Autowired
	PedidoReposotiry pedidoRepository; 
	
	@GetMapping
	public ModelAndView home(Model model, Principal principal) {
		
		/*
		Pedido pedido = new Pedido();
		pedido.setNomeProduto("Xiaomi Redmi Note 11");
		pedido.setValorNegociado(new BigDecimal(1350.00));
		pedido.setUrlImagem("https://m.media-amazon.com/images/I/51e3KdrHuCL._AC_SL1080_.jpg");
		pedido.setUrlProduto("https://www.amazon.com.br/Xiaomi-Redmi-Note-11-Graphite/dp/B09QSB4N2C/ref=sr_1_3?keywords=xiaomi+redmi+note+11&qid=1662079408&sprefix=Xiaomi+re%2Caps%2C131&sr=8-3&ufe=app_do%3Aamzn1.fos.25548f35-0de7-44b3-b28e-0f56f3f96147");
		pedido.setDescricao("Xiaomi Redmi Note 11 Graphite Gray 6GB Ram 128GB Rom");
		
		Pedido pedido2 = new Pedido();
		pedido2.setNomeProduto("Xiaomi Redmi Note 12");
		pedido2.setValorNegociado(new BigDecimal(1350.00));
		pedido2.setUrlImagem("https://m.media-amazon.com/images/I/51e3KdrHuCL._AC_SL1080_.jpg");
		pedido2.setUrlProduto("https://www.amazon.com.br/Xiaomi-Redmi-Note-11-Graphite/dp/B09QSB4N2C/ref=sr_1_3?keywords=xiaomi+redmi+note+11&qid=1662079408&sprefix=Xiaomi+re%2Caps%2C131&sr=8-3&ufe=app_do%3Aamzn1.fos.25548f35-0de7-44b3-b28e-0f56f3f96147");
		pedido2.setDescricao("Xiaomi Redmi Note 12 Graphite Gray 6GB Ram 128GB Rom");*/
		
		List<Pedido> pedidos =  pedidoRepository.findAllByUsuario(principal.getName());
		
		//List<Pedido> pedidos = Arrays.asList(pedido, pedido2);
		
		//model.addAttribute("pedidos", pedidos);
		
		//return "home";
		
	    ModelAndView mv = new ModelAndView("home");
	    mv.addObject("pedidos", pedidos);
	    return mv; 
	}
	
	@GetMapping("/{status}")
	public ModelAndView buscarPorStatus(@PathVariable("status") String status, Model model, Principal principal) {
		
		List<Pedido> pedidos =  pedidoRepository.findByStatusAndUserUserName(StatusPedido.valueOf(status.toUpperCase()), principal.getName());
		
	    ModelAndView mv = new ModelAndView("home");
	    mv.addObject("pedidos", pedidos);
	    mv.addObject("status", status);
	    return mv; 
	}
	
	@ExceptionHandler(IllegalArgumentException.class)
	public String onError() {
		return "redirect:/home";
	}

}
