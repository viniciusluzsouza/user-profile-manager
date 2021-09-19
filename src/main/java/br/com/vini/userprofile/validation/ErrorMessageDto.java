package br.com.vini.userprofile.validation;

public class ErrorMessageDto {
    private String mensagem;

    public ErrorMessageDto(String mensagem) {
	super();
	this.mensagem = mensagem;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
    
}
