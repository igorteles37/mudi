package br.com.alura.mvc.mudi.dto;

public class ExcecaoDTO {
	
	private String result;
	private String source;
	
	
	
	public ExcecaoDTO(String result, String source) {
		super();
		this.result = result;
		this.source = source;
	}

	public String getResult() {
		return result;
	}
	
	public String getSource() {
		return source;
	}
}
