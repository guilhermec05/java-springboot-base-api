package br.com.cabral.basic_api.exception;

public class UsuarioJaExisteException extends RuntimeException{
    public UsuarioJaExisteException(String mensagem){
        super(mensagem);
    }
}
