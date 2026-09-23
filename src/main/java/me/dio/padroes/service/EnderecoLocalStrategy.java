package me.dio.padroes.service;

import me.dio.padroes.model.Endereco;
import org.springframework.stereotype.Component;

/** Implementação local da estratégia, adequada para executar o projeto offline. */
@Component
public class EnderecoLocalStrategy implements EnderecoStrategy {

    @Override
    public Endereco buscarPorCep(String cep) {
        return new Endereco(cep, "Logradouro demonstrativo", "Belém", "PA");
    }
}
