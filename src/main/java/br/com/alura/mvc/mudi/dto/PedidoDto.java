package br.com.alura.mvc.mudi.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import br.com.alura.mvc.mudi.model.Pedido;

public class PedidoDto {
	
	
	private Long id;
	
	private String nomeProduto;
	private BigDecimal valorNegociado;
	private LocalDate dataDaEntrega;
	
	private String urlProduto;
	private String urlImagem;
	
	
	private String descricao;

	private String nomeUsuario;

	public PedidoDto() {
	}

	
		
	public PedidoDto(Pedido pedido) {
		this.id = pedido.getId();
		this.nomeProduto = pedido.getNomeProduto();
		this.valorNegociado = pedido.getValorNegociado();
		this.dataDaEntrega = pedido.getDataDaEntrega();
		this.urlProduto = pedido.getUrlProduto();
		this.urlImagem = pedido.getUrlImagem();
		this.descricao = pedido.getDescricao();
		this.nomeUsuario = pedido.getUser().getUserName();
	}





	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getNomeProduto() {
		return nomeProduto;
	}


	public void setNomeProduto(String nomeProduto) {
		this.nomeProduto = nomeProduto;
	}


	public BigDecimal getValorNegociado() {
		return valorNegociado;
	}


	public void setValorNegociado(BigDecimal valorNegociado) {
		this.valorNegociado = valorNegociado;
	}


	public LocalDate getDataDaEntrega() {
		return dataDaEntrega;
	}


	public void setDataDaEntrega(LocalDate dataDaEntrega) {
		this.dataDaEntrega = dataDaEntrega;
	}


	public String getUrlProduto() {
		return urlProduto;
	}


	public void setUrlProduto(String urlProduto) {
		this.urlProduto = urlProduto;
	}


	public String getUrlImagem() {
		return urlImagem;
	}


	public void setUrlImagem(String urlImagem) {
		this.urlImagem = urlImagem;
	}


	public String getDescricao() {
		return descricao;
	}


	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}



	public String getNomeUsuario() {
		return nomeUsuario;
	}



	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}
	
	
	

}
