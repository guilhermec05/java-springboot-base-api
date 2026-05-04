package br.com.cabral.basic_api.exception;

public class EmailBancoDiferenteDoCorpoException extends RuntimeException{
    public EmailBancoDiferenteDoCorpoException(String mensagem){
        super(mensagem);
    }
}
