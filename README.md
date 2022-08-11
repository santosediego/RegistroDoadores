# Registro de Doadores

[![NPM](https://img.shields.io/npm/l/react)](https://github.com/santosediego/RegistroDoadores/blob/main/LICENSE) 
[![Netlify Status](https://api.netlify.com/api/v1/badges/c064f2ad-b36c-4052-8121-d14475501253/deploy-status)](https://app.netlify.com/sites/vidaporvidas/deploys)

## Sobre o projeto

Registro de doadores é uma aplicação web desenvolvida a pedido de um grupo sem fins lucrativos, o grupo Vida por Vidas Coromandel, este grupo tem como
por projeto organizar e promover o levantamento de doadores de sangue. O sistema basicamente é um CRUD de pessoas que permite a exportação dos registros
por arquivo CSV e a impressão de um relatório PDF com os doadores posteriormente selecionados.

## Layout
<section align="center">
  <img src="https://raw.githubusercontent.com/santosediego/assets/main/RegistroDoadores/registroDoadoresHome.png" width="625" alt="Página principal">
  <img src="https://raw.githubusercontent.com/santosediego/assets/main/RegistroDoadores/registroDoadoresTelaEdicaoVisualizacao.png" width="625" alt="Tela de visualização">
  <img src="https://raw.githubusercontent.com/santosediego/assets/main/RegistroDoadores/registroDoadoresGerarRelatorio.png" width="625" alt="Página principal">
</section>

## Tecnologias utilizadas
### Back end
- Java
- Spring Boot
- JPA / Hibernate
- Maven
- OpenApi
### Front end
- HTML / CSS / TypeScript / Bootstrap
- ReactJS
- React-toastify
- Axios
- PdfMake
- DatePicker
- Dayjs
- API [Via Cep](https://viacep.com.br) para consulta do CEP

## Implantação em produção
- Back end: Heroku
- Front end: Netlify
- Banco de dados: PostgreSQL

## Como executar o projeto

### Back end
Pré-requisito: Java 17

```bash
# clonar repositório
git clone https://github.com/santosediego/RegistroDoadores.git

# entrar na pasta do projeto back end
cd RegistroDoadores/backend/

# executar o projeto (por padrão será executado com perfil de teste)
./mvnw spring-boot:run
```

### Front end
Pré-requisitos: 
- npm / yarn
- Back end em execução

```bash
# clonar repositório
git clone https://github.com/santosediego/dsmeta.git

# entrar na pasta do projeto front end web
cd RegistroDoadores/frontend/

# instalar dependências
yarn

# executar o projeto
yarn start
```

## Autor

[Diego Santos](https://www.linkedin.com/in/santosediego/ "Perfil Linkedin Diego Santos")
