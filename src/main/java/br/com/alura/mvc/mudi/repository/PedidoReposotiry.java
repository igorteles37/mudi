package br.com.alura.mvc.mudi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.alura.mvc.mudi.model.Pedido;

@Repository
public interface PedidoReposotiry extends JpaRepository<Pedido, Long>{

}
