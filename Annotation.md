# Camadas da Aplicação
A aplicação é dividida em camadas, cada uma com uma responsabilidade específica. Essa organização facilita a manutenção, a reutilização e a compreensão do código.

## Estrutura das camadas

### Controller
**Responsabilidade:** Receber as requisições HTTP e retornar as respostas ao cliente.
- Recebe dados enviados pelo cliente.
- Aciona a camada responsável pelo processamento.
- Retorna a resposta, geralmente em JSON.

### DTO (Data Transfer Object)
**Responsabilidade:** Transportar dados entre o cliente e a aplicação.
- Define quais dados serão recebidos ou enviados.
- É frequentemente utilizado na comunicação via JSON.
- Evita expor diretamente todas as informações de uma entidade.

### Model
**Responsabilidade:** Representar as entidades e os dados do domínio da aplicação.
- Define atributos e relacionamentos.
- Pode representar tabelas do banco de dados por meio de entidades JPA.

### Repository
**Responsabilidade:** Acessar e persistir os dados no banco de dados.
- Realiza operações de consulta, cadastro, atualização e exclusão.

### Service
**Responsabilidade:** Concentrar as regras de negócio da aplicação.
- Executa validações e regras de negócio.
- Coordena operações que envolvem uma ou mais entidades.
- Faz a comunicação entre o Controller e o Repository quando necessário.

> **Resumo para memorizar:**
> - **Controller:** recebe e responde.
> - **DTO:** transporta dados.
> - **Model:** representa as entidades.
> - **Repository:** acessa o banco.
> - **Service:** aplica as regras de negócio.

---

## 🔄 Fluxo básico de uma requisição
`GET /api/noticias → Tomcat → DispatcherServlet → Controller → Service → Repository → Jackson`

GET /api/noticias = Chega pela rede
Tomcat = Interpretar o texto do HTTP
DispatcherServlet = Consulta o mapa de rotas
Controller = Nosso código
Jackson = Converter o objeto em JSON

---

## Tipos de Declaração de Tipos Java
- Class: representa nosso usuário e seus atributos.
- Interface: define um contrato de métodos.
- Record: é útil para estruturas de dados imutáveis, como alguns DTOs.
- Enum: representa um conjunto fixo de opções.
- Annotation: cria uma anotação personalizada.
- Exception: representa uma exceção personalizada.