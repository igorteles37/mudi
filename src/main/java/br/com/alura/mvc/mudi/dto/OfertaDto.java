package br.com.alura.mvc.mudi.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import br.com.alura.mvc.mudi.model.Oferta;

public class OfertaDto {

	private Long id;
	private BigDecimal valor;
	private LocalDate dataDaEntrega;
	private String comentario;
	
	public OfertaDto(Oferta oferta) {
		this.id = oferta.getId();
		this.setComentario(oferta.getComentario());
		this.setValor(oferta.getValor());
		this.setDataDaEntrega(oferta.getDataDaEntrega());
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public BigDecimal getValor() {
		return valor;
	}
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	public LocalDate getDataDaEntrega() {
		return dataDaEntrega;
	}
	public void setDataDaEntrega(LocalDate dataDaEntrega) {
		this.dataDaEntrega = dataDaEntrega;
	}
	public String getComentario() {
		return comentario;
	}
	public void setComentario(String comentario) {
		this.comentario = comentario;
	}
	
	
	
}
