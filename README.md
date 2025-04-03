# Sistema de Recomendação de Filmes

Este projeto foi desenvolvido como parte do curso técnico em Informática para Internet na disciplina de Desenvolvimento Web III. Ele consiste em um sistema de recomendação de filmes, permitindo o cadastro e gerenciamento de filmes, avaliações e usuários. O objetivo principal é oferecer uma plataforma onde usuários possam avaliar filmes, registrar suas opiniões e obter recomendações com base em suas preferências.

## 🛠️ Tecnologias Utilizadas
- **Linguagem**: Java
- **Framework**: Spring Boot

## 📂 Estrutura do Projeto
O projeto segue a arquitetura MVC (Model-View-Controller) e está organizado da seguinte forma:

- **Controller**: Camada responsável por receber as requisições HTTP e direcioná-las para os serviços adequados.
  - `MovieController`, `ReviewController`, `UserController`
- **DTO (Data Transfer Object)**: Objetos para transferência de dados entre a aplicação e os clientes.
  - `MovieDTO`, `MovieRegisterDTO`, `ReviewDTO`, `ReviewRegisterDTO`, `UserDTO`
- **Model**: Representação das entidades do banco de dados.
  - `Movie`, `Review`, `User`
- **Repository**: Camada de persistência que interage com o banco de dados.
  - `MovieRepository`, `ReviewRepository`, `UserRepository`
- **Service**: Contém a lógica de negócios da aplicação.
  - `MovieService`, `ReviewService`, `UserService`
- **Validators**: Classe auxiliar para validação de campos.
  - `FieldsValidator`
- **MoviesApplication**: Classe principal que inicia a aplicação.

