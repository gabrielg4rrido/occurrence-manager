# OccurrenceManager

FileManager é uma aplicação  para gerenciar ocorrências. A aplicação utiliza basicamente Spring Boot.

## Tecnologias Utilizadas

- **Backend**: Spring Boot
- **Banco de dados**: PostgreSQL
- **Gerenciamento de Dependências**: Maven
- **Containerização**: Docker

## Pré-requisitos
- [Java JDK](https://www.oracle.com/java/technologies/javase-jdk17-downloads.html) (versão 17)
- [Maven](https://maven.apache.org/) (versão 3.8.4 ou superior)
- [Docker](https://www.docker.com/)

## Configuração do Ambiente
### Executando com Docker

1. Certifique-se de que o Docker está instalado e em execução.

2. Clone o repositório:

3. Navegue até o diretório raiz do projeto e execute o comando:

    ```sh
    docker-compose up --build
    ```

   Isso irá construir e iniciar os contêineres Docker para o backend, minIO e banco de dados.


4. Acesse a aplicação:

    - Back-end: [`http://localhost:8080`](command:_github.copilot.openSymbolFromReferences?%5B%22%22%2C%5B%7B%22uri%22%3A%7B%22scheme%22%3A%22file%22%2C%22authority%22%3A%22%22%2C%22path%22%3A%22%2Fhome%2Fgabrielgarrido%2FWorkspaces%2FPessoal%2Ffilemanager%2Ffront%2Ffile-manager%2Fsrc%2Fapp%2Fservice%2Fdiretorio.service.ts%22%2C%22query%22%3A%22%22%2C%22fragment%22%3A%22%22%7D%2C%22pos%22%3A%7B%22line%22%3A85%2C%22character%22%3A16%7D%7D%5D%2C%22ccba5f79-d892-4984-a7a1-b5068af09fea%22%5D "Go to definition")
    - MinIO: [`http://localhost:9001`](command:_github.copilot.openSymbolFromReferences?%5B%22%22%2C%5B%7B%22uri%22%3A%7B%22scheme%22%3A%22file%22%2C%22authority%22%3A%22%22%2C%22path%22%3A%22%2Fhome%2Fgabrielgarrido%2FWorkspaces%2FPessoal%2Ffilemanager%2Ffront%2Ffile-manager%2Fsrc%2Fapp%2Fservice%2Fdiretorio.service.ts%22%2C%22query%22%3A%22%22%2C%22fragment%22%3A%22%22%7D%2C%22pos%22%3A%7B%22line%22%3A85%2C%22character%22%3A16%7D%7D%5D%2C%22ccba5f79-d892-4984-a7a1-b5068af09fea%22%5D "Go to definition")
