package me.dio.padroes.service;

import java.util.List;

import me.dio.padroes.model.Cliente;
import me.dio.padroes.model.Endereco;
import me.dio.padroes.repository.ClienteRepository;
import org.springframework.stereotype.Service;

/** Facade: oferece operações simples e esconde repositório e consulta de endereço. */
@Service
public class ClienteFacade {

    private final ClienteRepository repository;
    private final EnderecoStrategy enderecoStrategy;

    public ClienteFacade(ClienteRepository repository, EnderecoStrategy enderecoStrategy) {
        this.repository = repository;
        this.enderecoStrategy = enderecoStrategy;
    }

    public List<Cliente> listarTodos() {
        return repository.findAll();
    }

    public Cliente buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ClienteNaoEncontradoException(id));
    }

    public Cliente criar(Cliente cliente) {
        cliente.setId(null);
        preencherEndereco(cliente);
        return repository.save(cliente);
    }

    public Cliente atualizar(Long id, Cliente dados) {
        Cliente cliente = buscarPorId(id);
        cliente.setNome(dados.getNome());
        cliente.setCep(dados.getCep());
        preencherEndereco(cliente);
        return repository.save(cliente);
    }

    public void excluir(Long id) {
        Cliente cliente = buscarPorId(id);
        repository.delete(cliente);
    }

    private void preencherEndereco(Cliente cliente) {
        Endereco endereco = enderecoStrategy.buscarPorCep(cliente.getCep());
        cliente.setEndereco(endereco);
    }
}
