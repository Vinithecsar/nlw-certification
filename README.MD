# Certification NLW

Este projeto é um sistema de certificação desenvolvido em Java utilizando o Spring Boot. O sistema permite que estudantes realizem provas, enviem respostas e verifiquem suas certificações em diversas tecnologias. O aplicativo também gera rankings com as melhores notas dos estudantes.

## Como Executar o Projeto

1. Clone este repositório.
2. Execute o comando:
   ```bash
   docker-compose up -d
3. Após o banco PostgreSQL começar a rodar no docker, execute o comando:
   ```bash
   mvn spring-boot:run
4. Acesse a API na URL http://localhost:8085, e o Swagger na URL http://localhost:8085/swagger-ui.html.

## Como gerar a documentação do projeto

1. Clone este repositório.
2. Execute o comando.
   ```bash
   mvn javadoc:javadoc
3. Abra o arquivo ./target/reports/apidocs/index-all.html

## Estrutura do Projeto

O projeto é organizado nas seguintes camadas:

- **Controllers**: Manipulam as requisições e respostas da API.
- **Repositories**: Interagem com o banco de dados para realizar operações CRUD.
- **Entities**: Contêm a modelagem das entidades do sistema.
- **useCases**: Contêm a lógica de negócios da aplicação, sendo as classes da camada "Service".
- **DTOs**: Objetos de transferência de dados que facilitam a comunicação entre as camadas.

## Controllers

### 1. **StudentController**

Gerencia as operações relacionadas aos estudantes.

- **POST** `/students/verifyIfHasCertification`
  - **Descrição**: Verifica se o estudante já possui uma certificação.
  - **Corpo da Requisição**:
    ```json
    {
      "email": "exemplo@dominio.com",
      "technology": "Java"
    }
    ```
  - **Resposta**: Retorna uma mensagem informando se o usuário já fez a prova ou se pode fazê-la.

- **POST** `/students/certification/answer`
  - **Descrição**: Envia as respostas do estudante para a certificação.
  - **Corpo da Requisição**:
    ```json
    {
      "email": "exemplo@dominio.com",
      "technology": "Java",
      "questionAnswers": [
        {"questionId": "uuid_pergunta", "alternativeId": "uuid_alternativa"}
      ]
    }
    ```
  - **Resposta**: Retorna um objeto com o resultado do processamento das respostas ou um erro, se houver.

### 2. **AlternativeController**

Gerencia as requisições relacionadas às alternativas.

- **POST** `/alternatives`
  - **Descrição**: Cria uma nova alternativa com os dados fornecidos no corpo da requisição.
  - **Parâmetros**: 
    - `dto` (objeto) - Objeto contendo os dados da alternativa a ser criada.
  - **Resposta**: Retorna um objeto AlternativesResultDTO contendo os dados da alternativa criada.
  - **Exceções**: Lança uma exceção se ocorrer um erro durante o processamento da requisição.


### 3. **QuestionController**

Gerencia as requisições relacionadas às questões.

- **POST** `/questions`
  - **Descrição**: Cria uma nova questão com os parâmetros passados no corpo da requisição.
  - **Parâmetros**: 
    - `dto` (objeto) - Objeto que contém as informações necessárias para criar a questão.
  - **Resposta**: Retorna a questão criada, do tipo QuestionResultDTO.

- **GET** `/questions/technology/{technology}`
  - **Descrição**: Busca questões relacionadas a uma determinada tecnologia.
  - **Parâmetros**: `technology` (string) - O nome da tecnologia a ser buscada.
  - **Resposta**: Retorna uma lista de questões com suas alternativas.

### 4. **RankingController**

Gera o ranking dos estudantes com as melhores notas.

- **GET** `/ranking/top10`
  - **Descrição**: Retorna uma lista com os usuários que estão no top 10 de questões respondidas.
  - **Resposta**: Uma lista de estudantes (até 10) com as melhores notas.

## Estrutura do Banco de Dados

O banco de dados utiliza o PostgreSQL e contém as seguintes tabelas principais:

- **students**: Armazena informações dos estudantes.
- **certifications**: Armazena informações sobre provas/certificações e as notas dos estudantes.
- **answers_certifications_students**: Armazena as respostas dos estudantes para as provas/certificações.
- **questions**: Armazena as questões.
- **alternatives**: Armazena as alternativas disponíveis para as questões.
