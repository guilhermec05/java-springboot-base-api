package br.com.cabral.basic_api.exception;

public class UsuarioNaoEncontradoException extends RuntimeException{
    public UsuarioNaoEncontradoException(String mensagem){
        super(mensagem);
    }
}
