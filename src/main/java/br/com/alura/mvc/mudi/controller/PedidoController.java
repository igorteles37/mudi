package br.com.alura.mvc.mudi.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.HttpClientErrorException.MethodNotAllowed;
import org.springframework.web.servlet.ModelAndView;

import br.com.alura.mvc.mudi.controller.form.PedidoForm;
import br.com.alura.mvc.mudi.model.Pedido;
import br.com.alura.mvc.mudi.model.User;
import br.com.alura.mvc.mudi.repository.PedidoReposotiry;
import br.com.alura.mvc.mudi.repository.UserRepository;

@Controller
@RequestMapping("pedido")
public class PedidoController {


	@Autowired
	PedidoReposotiry pedidoRepository;
	
	@Autowired
	UserRepository userRepository;
	
	
	@GetMapping("formulario")
	public String formulario(PedidoForm pedidoForm) {
		return "pedido/formulario";
	}
	
	@PostMapping("inserir")
	@CacheEvict(value = "pedidos_home", allEntries = true)
	public ModelAndView inserir(@Valid PedidoForm pedidoForm, BindingResult result) {

		ModelAndView mv;
		
		if(result.hasErrors()) {
			return mv = new ModelAndView("pedido/formulario");
		}
			
		String userName = SecurityContextHolder.getContext().getAuthentication().getName();
		
		Pedido pedido  = pedidoForm.toPedido();
		
		Optional<User> user = userRepository.findById(userName);
		
		if (user.isPresent())
			pedido.setUser(user.get());
		else 
			pedido.setUser(null);
		
		pedidoRepository.save(pedido);
		mv =  new ModelAndView("usuario/home");
		mv.addObject("mensagem", "Sucesso");

		return mv;
	}
	
	@ExceptionHandler(MethodNotAllowed.class)
	public String onError() {
		return "redirect:/usuario/pedidos";
	}
	
	@CacheEvict(value = "pedidos_home", allEntries = true)
	@GetMapping("delete/{id}")
	public String delete(@PathVariable Long id) {
		
		Optional<Pedido> pedido =  pedidoRepository.findById(id);
		
		if (pedido.isPresent()) 
			pedidoRepository.delete(pedido.get());
		
			
		return "redirect:/usuario/pedidos";
	}
	
	
	@GetMapping("atualizarform/{id}")
	public String atualizarForm(PedidoForm pedidoForm, Model model, @PathVariable Long id) {
		
		Pedido pedido =  pedidoRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid pedido Id:" + id));;
		
		/*PedidoForm pedidoForm = new PedidoForm();
		pedidoForm.setNomeProduto(pedido.getNomeProduto());
		pedidoForm.setUrlProduto(pedido.getUrlProduto());
		pedidoForm.setUrlImagem(pedido.getUrlImagem());
		pedidoForm.setDescricao(pedido.getDescricao());
		pedidoForm.setId(pedido.getId());*/
		
		model.addAttribute("pedidoForm", new PedidoForm(pedido.getNomeProduto(), pedido.getUrlProduto(), pedido.getUrlImagem(), pedido.getDescricao(), pedido.getId()));
			
		return "pedido/formulario_atualizacao";
	}
	
	@CacheEvict(value = "pedidos_home", allEntries = true)
	@PostMapping("atualizar/{id}")
	public String atualizar(@Valid PedidoForm pedidoForm, BindingResult result, @PathVariable Long id, Model model) {
		
		if (result.hasErrors()) {
			return "pedido/formulario_atualizacao";
		}
		
	
        Pedido pedidoOriginal = pedidoRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid pedido Id:" + id));
        
        pedidoOriginal.setNomeProduto(pedidoForm.getNomeProduto());
        pedidoOriginal.setUrlProduto(pedidoForm.getUrlProduto());
        pedidoOriginal.setUrlImagem(pedidoForm.getUrlImagem());
        pedidoOriginal.setDescricao(pedidoForm.getDescricao());
        
        pedidoRepository.save(pedidoOriginal);
        return "redirect:/usuario/pedidos"; 
        
		
	}

}
