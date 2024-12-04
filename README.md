# Spring Boot Application Setup

Este guia explica como baixar, configurar e executar um projeto backend usando Spring Boot com MySQL.

## Pré-requisitos

Antes de começar, certifique-se de ter os seguintes requisitos instalados:

1. **Java JDK 17** ou superior:
    - Baixe e instale o Java Development Kit (JDK) 17 ou superior do [site oficial da Oracle](https://www.oracle.com/java/technologies/javase-downloads.html) ou do [OpenJDK](https://openjdk.org/).
    - Verifique a instalação com o comando:
      ```bash
      java -version
      ```

2. **Maven**:
    - O Maven pode ser instalado separadamente ou usado a partir do wrapper incluso no projeto (`./mvnw`).
    - Verifique a versão instalada com:
      ```bash
      mvn -v
      ```

3. **MySQL**:
    - Baixe e instale o MySQL Community Server do [site oficial](https://dev.mysql.com/downloads/).
    - Configure um banco de dados chamado `cliente-servidor` (detalhes abaixo).

## Clonar o Repositório

Baixe o código-fonte do projeto usando Git:

```bash
git clone <URL_DO_REPOSITORIO>
```
Substitua `<URL_DO_REPOSITORIO>` pelo link do repositório do projeto.

## Configuração do Banco de Dados

1. Certifique-se de que o serviço MySQL está em execução.
2. Crie um banco de dados chamado `cliente-servidor`.
3. Ajuste o usuário e a senha no arquivo `application.properties` conforme necessário.

## Configurações no `application.properties`

O arquivo `application.properties` já contém as seguintes configurações padrão:

```properties
spring.application.name=cliente-servidor

# Configurações do banco de dados MySQL
spring.datasource.url=jdbc:mysql://localhost:porta_mysql/nome_banco
spring.datasource.username=usuario_banco
spring.datasource.password=senha_banco
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

# Configurações JPA / Hibernate
spring.jpa.show-sql=true
spring.jpa.hibernate.ddl-auto=none
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect

# Flyway Migration
flyway.user=usuario_banco
flyway.password=senha_banco
flyway.schemas=nome_banco
flyway.url=jdbc:mysql://localhost:porta_mysql/nome_banco
flyway.locations=filesystem:db/migration
spring.flyway.enabled=true
```

Certifique-se de que os valores de `spring.datasource.username` e `spring.datasource.password` correspondem ao usuário e senha configurados no MySQL.

Rode o projeto na IDE desejada.
