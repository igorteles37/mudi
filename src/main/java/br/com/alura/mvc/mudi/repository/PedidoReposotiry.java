package br.com.alura.mvc.mudi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.alura.mvc.mudi.model.Pedido;
import br.com.alura.mvc.mudi.model.StatusPedido;

@Repository
public interface PedidoReposotiry extends JpaRepository<Pedido, Long>{

	
	List<Pedido> findByStatusAndUserUserName(StatusPedido status, String username);
	
	@Query("SELECT p FROM Pedido p "
			+ "JOIN FETCH p.user u "
			+ "WHERE u.userName = :username")
	List<Pedido> findAllByUsuario(@Param("username") String username);
	
}
