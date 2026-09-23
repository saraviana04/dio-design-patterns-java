package me.dio.padroes.service;

public class ClienteNaoEncontradoException extends RuntimeException {

    public ClienteNaoEncontradoException(Long id) {
        super("Cliente de ID " + id + " não foi encontrado");
    }
}
