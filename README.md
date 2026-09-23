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

1. Inicie a aplicação com `mvn spring-boot:run` e aguarde o servidor subir na porta `8080`.
2. No Bruno, clique em **Open Collection** e selecione a pasta `bruno` deste projeto.
3. Selecione o ambiente **local**, cuja variável `baseUrl` aponta para `http://localhost:8080`.

### Criar um cliente

Abra a requisição **Criar cliente** ou configure manualmente:

- Método: `POST`
- URL: `http://localhost:8080/clientes`
- Header: `Content-Type: application/json`
- Body: `JSON`

```json
{
  "nome": "Maria Silva",
  "cep": "66000000"
}
```

A resposta esperada é `201 Created`, contendo o cliente criado e seu endereço demonstrativo.

> Não use `GET http://localhost:8080` para criar o cliente. A aplicação não possui uma rota `/`,
> portanto essa chamada retorna `404 Not Found`.

### Listar os clientes

Abra a requisição **Listar clientes** ou utilize:

```text
GET http://localhost:8080/clientes
```

Essa requisição não precisa de body e deve retornar `200 OK`.

### Outros testes

| Método | URL | Resultado esperado |
|---|---|---|
| `GET` | `http://localhost:8080/clientes/1` | Busca o cliente de ID 1 |
| `PUT` | `http://localhost:8080/clientes/1` | Atualiza o cliente de ID 1 usando um body JSON |
| `DELETE` | `http://localhost:8080/clientes/1` | Exclui o cliente e retorna `204 No Content` |

Para testar a validação, envie um `POST` com um CEP que não tenha exatamente oito números.
A API deverá responder com `400 Bad Request`.
