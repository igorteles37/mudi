package br.com.alura.mvc.mudi.api;

import java.net.URI;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.alura.mvc.mudi.dto.ExcecaoDTO;
import br.com.alura.mvc.mudi.dto.OfertaDto;
import br.com.alura.mvc.mudi.dto.PedidoDto;
import br.com.alura.mvc.mudi.dto.RequisicaoNovaOferta;
import br.com.alura.mvc.mudi.model.Oferta;
import br.com.alura.mvc.mudi.model.Pedido;
import br.com.alura.mvc.mudi.repository.OfertaReposotiry;
import br.com.alura.mvc.mudi.repository.PedidoReposotiry;

@RestController
@RequestMapping("/api/ofertas")
public class OfertasRest {

	@Autowired
	private PedidoReposotiry pedidoRepository;
	
	@Autowired
	private OfertaReposotiry ofertaRepository;
	
	@PostMapping
	public ResponseEntity<?> criaOferta(@Valid @RequestBody RequisicaoNovaOferta requisicao, UriComponentsBuilder uriBuilder) {
	
			Optional<Pedido> pedidoBuscado = pedidoRepository.findById( requisicao.getPedidoId());
			if (!pedidoBuscado.isPresent()) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND)
						.body(new ExcecaoDTO(this.getClass().getName(), "Tópico não encontrado"));
			}
			
			Pedido pedido = pedidoBuscado.get();
			Oferta oferta = requisicao.toOferta();
			oferta.setPedido(pedido);
			ofertaRepository.save(oferta);

			
			/*pedido.getOfertas().add(oferta);
			pedidoRepository.save(pedido);*/
			
			
			URI uri = uriBuilder.path("/api/ofertas/{id}").buildAndExpand(oferta.getId()).toUri();
			
			return ResponseEntity.created(uri).body(new OfertaDto(oferta));
	}
	

	@GetMapping("{id}")
	public ResponseEntity<?> obterOferta(@PathVariable Long id){
		Optional<Oferta> ofertaBuscada = ofertaRepository.findById(id);
		if (!ofertaBuscada.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(new ExcecaoDTO(this.getClass().getName(), "Oferta não encontrada"));
		}
		
		
		return ResponseEntity.ok(new OfertaDto(ofertaBuscada.get()));
		
		
	}

	
}
