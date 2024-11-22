# Sistema de Seguros


## Pré-requisitos
-Java 17 ou superior                                                                                                                       
-Maven (para gerenciamento de dependências)                            
-PostgreSQL (banco de dados)


-**Instalação**

**Clonando o repositorio e o encontrando**
```
git clone <URL_DO_REPOSITORIO_GIT>
cd sistema_seguros
```
**Instalando dependências:**
```
mvn clean install
```
## Configuração do Banco de Dados
**Crie o Banco e configure o seu usuario**
```
CREATE DATABASE sistema_seguros;
CREATE USER sistema_seguros_user
WITH PASSWORD 'sua_senha';

ALTER DATABASE sistema_seguros
OWNER TO sistema_seguros_user;
```

**Configure as credenciais no arquivo src/main/resources/application.properties:**
```
spring.datasource.url=jdbc:postgresql://localhost:5432/ProjetoDeSeguros
spring.datasource.username=user_seguro
spring.datasource.password=sua_senha
```
Execução
```
mvn spring-boot:run
```
A aplicação estará disponível em: `http://localhost:8080.`

## Ordem de Requisições

1. Corretor
2. Segurado
3. Apólice
4. Sinistro
5. Reclamante

Exemplos de Requisições


### **Corretor**
- *Crie os corretores primeiro, pois eles serão associados às apólices que eles gerenciam*
- Endpoint:
    POST `/api/corretor`
- Corpo da requisição:
```java
{
  "nome": "João da Silva",
  "cnpj": "12345678901234",
  "email": "joao.silva@example.com",
  "telefone": "11912345678",
}
```

### **Segurado**
-*Crie os segurados, pois eles serão os proprietários das apólices.*
- Endpoint:
    POST `/api/segurado` 
```java
{
  "nome": "Carlos Almeida",
  "cpfCnpj": "12345678901",
  "endereco": "Rua Exemplo, 456",
  "telefone": "11998765432",
  "email": "carlos.almeida@example.com"
}
```

### **Apólice**
- *Crie as apólices, associando-as a um segurado e a um corretor.*
- Endpoint:
    POST `/api/apolice`
- Corpo da requisição:
```java
{
  "numero": "APO123456",
  "tipo": "Automóvel",
  "valor": 50000.00,
  "dataInicio": "2024-01-01",
  "dataFim": "2024-12-31",
  "segurado": { "id": 1 },
  "corretor": { "id": 1 }
}
```

### **Sinistro**
-*Registre os sinistros, associando-os a uma apólice.*
- Endpoint:
    POST `/api/sinistro`
- Corpo da requisição:
```java
{
  "dataOcorrencia": "2024-06-15",
  "descricao": "Acidente de carro",
  "valorReclamado": 50000.0,
  "apolice": { "id": 1}
```

### **Reclamante**
-*Crie os reclamantes, associando-os aos sinistros nos quais estão envolvidos.*
- Endpoint:
    POST `/api/reclamante`
- Corpo da requisição:
```java
{
  "nome": "Maria Silva",
  "cpfCnpj": "12345678902",
  "telefone": "11911111111",
  "email": "maria.silva@example.com",
  "sinistro": {"id": 1 }
}
```
## **Testes no Postman**

1. Importe a documentação da API no Postman usando a URL do OpenAPI (Swagger): `http://localhost:8080/api-docs.json`
2. Configure a variável {{base_url}} como `http://localhost:8080`.

## **Documentação**

Agora rodando o projeto encontrará a documentação referente ao Swagger em: http://localhost:8080/docs.
