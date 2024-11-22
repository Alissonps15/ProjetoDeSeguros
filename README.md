Pré-requisitos

1. Java 17 ou superior
2. Maven (para gerenciamento de dependências)
3. PostgreSQL (banco de dados)

Instalação

1. Clonar o repositório: git clone <URL_DO_REPOSITORIO_GIT>
2. Entrar na pasta do projeto: cd sistema_seguros
3. Instalar dependências: mvn clean install

Configuração do Banco de Dados

1. Iniciar o PostgreSQL e criar o banco de dados: CREATE DATABASE sistema_seguros;
2. Criar um usuário específico para o projeto (opcional):

CREATE USER sistema_seguros_user WITH PASSWORD 'sua_senha';
ALTER DATABASE sistema_seguros OWNER TO sistema_seguros_user;

1. Configurar as credenciais no arquivo src/main/resources/application.properties:

spring.datasource.url=jdbc:postgresql://localhost:5432/sistema_seguros
spring.datasource.username=sistema_seguros_user
spring.datasource.password=sua_senha


Execução

1. Rodar o projeto: mvn spring-boot:run
2. A aplicação estará disponível em: http://localhost:8080.

Ordem de Requisições

Para evitar erros de relacionamento, siga esta ordem para as requisições POST:

1. Corretor: Crie os corretores primeiro.

{
 "nome": "João da Silva",
 "cnpj": "12345678901234",
 "email": "joao.silva@example.com"
}

1. Segurado: Crie os segurados após os corretores.

{
 "nome": "Carlos Almeida",
 "cpfCnpj": "12345678901",
 "endereco": "Rua Exemplo, 123",
 "telefone": "11912345678",
 "email": "carlos.almeida@example.com"
}

1. Apólice: Crie as apólices e associe os segurados e corretores.

{
 "numero": "AP12345",
 "dataInicio": "2024-01-01",
 "dataFim": "2024-12-31",
 "valorCobertura": 100000.0,
 "premio": 500.0,
 "segurado": { "id": 1 },
 "corretor": { "id": 1 }
}

1. Sinistro: Crie os sinistros após as apólices.

{
 "dataOcorrencia": "2024-06-15",
 "descricao": "Acidente de carro",
 "valorReclamado": 50000.0,
 "apolice": { "id": 1 }
}

1. Reclamante: Crie os reclamantes após os sinistros.

{
 "nome": "Maria Silva",
 "cpfCnpj": "12345678902",
 "telefone": "11998765432",
 "email": "maria.silva@example.com",
 "sinistro": { "id": 1 }
}


Testes no Postman

1. Importe a documentação da API no Postman usando a URL do OpenAPI (Swagger): http://localhost:8080/api-docs.json
2. Configure a variável {{base_url}} como http://localhost:8080 para facilitar o uso dos endpoints.
3. Execute as requisições seguindo a ordem definida acima para evitar erros de relacionamento.

Documentação

A documentação completa da API é gerada automaticamente pelo Swagger e pode ser acessada em: http://localhost:8080/docs.
