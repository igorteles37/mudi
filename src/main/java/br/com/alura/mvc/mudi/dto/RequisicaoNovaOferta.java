package br.com.alura.mvc.mudi.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import br.com.alura.mvc.mudi.model.Oferta;

public class RequisicaoNovaOferta {
	
	private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
 
// @formatter:on
 
	
	
	private Long pedidoId;
	
	//@Pattern(message = "Valor inválido.", regexp = "^\\d+(\\.\\d{2})?$")
	@NotNull(message = "Campo valor não pode ser vazio.")
	private BigDecimal valor;
	
	@NotNull(message = "Campo data não pode ser vazio")
	@Pattern(regexp = "^\\d{2}/\\d{2}/\\d{4}$", message = "Data inválida.")
	private String dataDaEntrega;
	
	
	private String comentario;
	
	public Long getPedidoId() {
		return pedidoId;
	}
	public void setPedidoId(Long pedidoId) {
		this.pedidoId = pedidoId;
	}
	public BigDecimal getValor() {
		return valor;
	}
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	public String getDataDaEntrega() {
		return dataDaEntrega;
	}
	public void setDataDaEntrega(String dataDaEntrega) {
		this.dataDaEntrega = dataDaEntrega;
	}
	public String getComentario() {
		return comentario;
	}
	public void setComentario(String comentario) {
		this.comentario = comentario;
	}
	public Oferta toOferta() {

		Oferta oferta = new Oferta();
		oferta.setComentario(this.comentario);
		oferta.setDataDaEntrega(LocalDate.parse(this.dataDaEntrega, formatter));
		oferta.setValor(this.valor);
		return oferta;
	}
	
	
	
}
