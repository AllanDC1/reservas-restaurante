[SPRING_BADGE]: https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white
[JAVA_BADGE]: https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white
[POSTGRES_BADGE]: https://img.shields.io/badge/postgres-%23316192.svg?style=for-the-badge&logo=postgresql&logoColor=white
[HIBERNATE_BADGE]: https://img.shields.io/badge/Hibernate-59666C?style=for-the-badge&logo=Hibernate&logoColor=white
[JWT_BADGE]: https://img.shields.io/badge/JWT-black?style=for-the-badge&logo=JSON%20web%20tokens
[MAVEN_BADGE]: https://img.shields.io/badge/apachemaven-C71A36.svg?style=for-the-badge&logo=apachemaven&logoColor=white
[DOCKER_BADGE]: https://img.shields.io/badge/docker-%230db7ed.svg?style=for-the-badge&logo=docker&logoColor=white
[SWAGGER_BADGE]: https://img.shields.io/badge/-Swagger-%23Clojure?style=for-the-badge&logo=swagger&logoColor=white

<h1 align="center" style="font-weight: bold;">API de Reservas para Restaurante 💻</h1>

![spring][SPRING_BADGE]
![java][JAVA_BADGE]
![postgres][POSTGRES_BADGE]
![hibernate][HIBERNATE_BADGE]
![jwt][JWT_BADGE]
![maven][MAVEN_BADGE]
![docker][DOCKER_BADGE]
![swagger][SWAGGER_BADGE]

<b>Desafio do site [racoelho.com.br](https://racoelho.com.br/listas/desafios/sistema-de-reservas-de-restaurante)</b>

<details open="open">
<summary>Sumário</summary>
  
- [📍 Setup](#start)
  - [Pré-requisitos](#prerequisites)
  - [Clonando o projeto](#cloning)
  - [Variáveis de ambiente (opcional)](#environment-variables)
  - [Rodando o sistema](#run)
- [🌐 Desenvolvedor](#dev)
  
</details>

<h2 id="start">📍 Setup</h2>

Essa é uma API REST desenvolvida em **Java + Spring Boot**, que gerencia reservas de um restaurante, permitindo cadastro de usuários, autenticação via JWT e controle de mesas e reservas.

<h3 id="prerequisites">Pré-requisitos</h3>

Certifique-se de ter instalado:

- [Git](https://git-scm.com)
- [Docker](https://www.docker.com/)
- [Java 21+](https://www.oracle.com/br/java/technologies/downloads/)

<h3 id="cloning">Clonando o projeto</h3>

Abra o terminal no diretório onde quiser clonar o projeto e cole o comando:

```bash
git clone https://github.com/AllanDC1/reservas-restaurante.git
```

Depois mude para o diretório recém criado com:

```bash
cd reservas-restaurante
```

<h3 id="environment-variables">Variáveis de ambiente (opcional)</h2>

Se quiser rodar localmente (sem utilizar o docker), crie um arquivo `application.properties` na pasta `src/main/resources`.<br>
Use como referência o arquivo `application.properties.example`:

```yaml
spring.datasource.url=jdbc:postgresql://localhost:5432/reservasdb
spring.datasource.username=postgres
spring.datasource.password=postgres

jwt.secret={SUA_CHAVE_SECRETA}
```

<h3 id="run">Rodando o sistema</h3>

Usando docker, no diretório do projeto, apenas cole no terminal:

```bash
docker-compose up --build
```

Assim a API e o banco de dados (PostgreSQL) já serão construídos e conectados.<br>

A aplicação já estará rodando na porta padrão (8080) em:
[http://localhost:8080/](http://localhost:8080/)

E a documentação completa dos endpoints da API estará disponível no Swagger em:
[http://localhost:8080/swagger-ui](http://localhost:8080/swagger-ui)  

<h2 id="dev">🌐 Desenvolvedor</h2>

<a href="https://github.com/AllanDC1">
    <img src="https://avatars.githubusercontent.com/u/162832747?v=4" width="100px;" alt="Allan Donetti Calen Profile Picture"/><br>
    <sub>
        <b>Allan Donetti Calen</b>
    </sub>
</a>
<br><br>
<a href="https://www.linkedin.com/in/allan-donetti-calen">
    <img src="https://img.shields.io/badge/-LinkedIn-%230077B5?style=flat-square&logo=Linkedin&logoColor=white">
</a>
<a href="mailto:adonetticalen@gmail.com">
    <img src="https://img.shields.io/badge/-adonetticalen@gmail.com-D14836?style=flat-square&logo=Gmail&logoColor=white">
</a>