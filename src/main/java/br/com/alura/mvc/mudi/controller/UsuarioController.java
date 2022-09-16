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
@RequestMapping("usuario")
public class UsuarioController {
	
	@Autowired
	PedidoReposotiry pedidoRepository;
	
	@GetMapping("pedidos")
	public String home(Model model, Principal principal) {

		List<Pedido> pedidos =  pedidoRepository.findAllByUsuario(principal.getName());
		
		//List<Pedido> pedidos = Arrays.asList(pedido, pedido2);
	    //ModelAndView mv = new ModelAndView("usuario/home");
	    
	    //mv.addObject("pedidos", pedidos);
		model.addAttribute("pedidos", pedidos);
	    return "usuario/home"; 
	}
	
	@GetMapping("pedidos/{status}")
	public String buscarPorStatus(@PathVariable("status") String status, Model model, Principal principal) {
		
		List<Pedido> pedidos =  pedidoRepository.findByStatusAndUserUserName(StatusPedido.valueOf(status.toUpperCase()), principal.getName());
		
	    ModelAndView mv = new ModelAndView("usuario/home");
	    mv.addObject("pedidos", pedidos);
	    mv.addObject("status", status);
	    
	    model.addAttribute("pedidos", pedidos);
	    model.addAttribute("status", status);

	    return "usuario/home"; 
	}
	
	@ExceptionHandler(IllegalArgumentException.class)
	public String onError() {
		return "redirect:/usuario/pedidos";
	}

}
