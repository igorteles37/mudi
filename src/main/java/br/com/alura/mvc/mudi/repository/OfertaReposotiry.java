package br.com.alura.mvc.mudi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.alura.mvc.mudi.model.Oferta;

@Repository
public interface OfertaReposotiry extends JpaRepository<Oferta, Long>{

	
	
	public List<Oferta> findByPedidoId(Long idPedido);
	
	@Query("SELECT o FROM Oferta o "
			+ " JOIN FETCH o.pedido p "
			+ "WHERE p.id = :idPedido")
	public List<Oferta> obterOfertasPorPedido(@Param("idPedido") Long idPedido);
		
}
