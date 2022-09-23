package br.com.alura.mvc.mudi.api;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.alura.mvc.dto.PedidoDto;
import br.com.alura.mvc.mudi.model.Pedido;
import br.com.alura.mvc.mudi.model.StatusPedido;
import br.com.alura.mvc.mudi.repository.PedidoReposotiry;


@RestController
@RequestMapping("/api/pedidos")
public class PedidosRest {
	
	@Autowired
	private PedidoReposotiry pedidoRepository;
	
	@GetMapping
	public ResponseEntity<?> listar(@RequestParam(required = false) Long id){
		
		if (id != null) {
			Optional<Pedido> pedido = pedidoRepository.findById(id);
			if (pedido.isPresent())
				return ResponseEntity.ok().body(new PedidoDto(pedido.get()));
			else
				return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		} else {
			List<Pedido> pedidos = pedidoRepository.findAll();
			List<PedidoDto> pedidosDto =  new ArrayList<>();
			
			pedidos.forEach(p -> pedidosDto.add(new  PedidoDto(p)));
			
			return ResponseEntity.ok().body(pedidosDto);
		}
	}
	
	@GetMapping("aguardando")
	public ResponseEntity<List<PedidoDto>> getPedidosAguardandoOfertas(){
		Sort sort = Sort.by("id").descending();
		PageRequest paginacao = PageRequest.of(0, 5, sort);
		
		List<Pedido> pedidos = pedidoRepository.findByStatus(StatusPedido.AGUARDANDO, paginacao);
		
		List<PedidoDto> pedidosDto =  new ArrayList<>();
		pedidos.forEach(p -> pedidosDto.add(new  PedidoDto(p)));
		return ResponseEntity.ok().body(pedidosDto) ;
	}

}
