package br.com.alura.mvc.mudi.controller.form;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import br.com.alura.mvc.mudi.model.Pedido;
import br.com.alura.mvc.mudi.model.StatusPedido;

public class PedidoForm {
	
	@NotBlank 
	private String nomeProduto;
	
	@NotBlank 
	private String urlProduto;
	
	@NotBlank 
	private String urlImagem;
	
	
	private String descricao;
	
	private final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	
	public String getNomeProduto() {
		return nomeProduto;
	}
	public void setNomeProduto(String nomeProduto) {
		this.nomeProduto = nomeProduto;
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
	public Pedido toPedido() {
		// TODO Auto-generated method stub
		LocalDate dataEntrega = LocalDate.parse("01/12/2023", dateFormatter);
		Pedido pedido = new Pedido();
		
		pedido.setNomeProduto(this.getNomeProduto());
		pedido.setUrlProduto(this.getUrlProduto());
		pedido.setUrlImagem(this.getUrlImagem());
		pedido.setDescricao(this.getDescricao());
		pedido.setStatus(StatusPedido.AGUARDANDO);
		pedido.setDataDaEntrega(dataEntrega);
		pedido.setValorNegociado(new BigDecimal(5599.98));
		
		return pedido;
		
	}
	


	
	

}
