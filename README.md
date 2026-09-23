# Explorando Padrões de Projeto com Java

Projeto desenvolvido para o desafio da DIO **Explorando Padrões de Projetos na Prática com Java**.
A aplicação é uma API REST para cadastro de clientes e preenchimento de endereço a partir do CEP.

## Padrões utilizados

- **Singleton:** por padrão, o Spring cria uma única instância de cada `@Service`, `@Component` e `@Repository` no contêiner de injeção de dependências.
- **Strategy:** `EnderecoStrategy` define o contrato de busca de endereço. A implementação `EnderecoLocalStrategy` pode ser substituída por ViaCEP sem mudar a regra de cadastro.
- **Facade:** `ClienteFacade` disponibiliza operações simples e concentra a comunicação com o repositório e a estratégia de endereço.
- **Repository:** `ClienteRepository` abstrai o acesso ao banco usando Spring Data JPA.

## Tecnologias

- Java 21
- Spring Boot
- Spring Web
- Spring Data JPA
- Bean Validation
- Banco H2
- Maven e JUnit 5

## Executando

```bash
mvn spring-boot:run
```

A API ficará disponível em `http://localhost:8080`. O console do H2 estará em
`http://localhost:8080/h2-console` (JDBC URL: `jdbc:h2:mem:clientes`, usuário: `sa`, sem senha).

## Endpoints

| Método | Caminho | Ação |
|---|---|---|
| GET | `/clientes` | Listar clientes |
| GET | `/clientes/{id}` | Buscar um cliente |
| POST | `/clientes` | Criar um cliente |
| PUT | `/clientes/{id}` | Atualizar um cliente |
| DELETE | `/clientes/{id}` | Excluir um cliente |

Exemplo de cadastro:

```bash
curl -i -X POST http://localhost:8080/clientes \
  -H 'Content-Type: application/json' \
  -d '{"nome":"Maria Silva","cep":"66000000"}'
```

## Testes

```bash
mvn test
```

## Testando com Bruno

No Bruno, clique em **Open Collection** e selecione a pasta `bruno` deste projeto.
Selecione o ambiente `local`, abra **Criar cliente** e clique em **Send**.
