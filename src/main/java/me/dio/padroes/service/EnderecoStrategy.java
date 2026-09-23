package me.dio.padroes.service;

import me.dio.padroes.model.Endereco;

/** Strategy: permite trocar a forma de consulta sem alterar o cadastro de clientes. */
public interface EnderecoStrategy {

    Endereco buscarPorCep(String cep);
}
