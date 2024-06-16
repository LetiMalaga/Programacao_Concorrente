# Biblioteca Socket Server

Este projeto implementa um servidor de biblioteca utilizando sockets em Java, permitindo o gerenciamento de um registro de livros através de operações como listagem, cadastro, aluguel e devolução. A comunicação entre cliente e servidor é realizada por meio de sockets e os dados são armazenados e recuperados de um arquivo JSON.

## Funcionalidades

- **Listagem dos livros**: Permite ao cliente solicitar a listagem de todos os livros disponíveis na biblioteca.
- **Cadastro de livros**: Permite ao cliente cadastrar novos livros na biblioteca.
- **Aluguel de livros**: Permite ao cliente alugar livros, reduzindo o número de exemplares disponíveis.
- **Devolução de livros**: Permite ao cliente devolver livros, aumentando o número de exemplares disponíveis.

## Estrutura do Projeto

### Classe `Livro`

Representa um livro na biblioteca com os seguintes atributos:
- `autor`: Autor do livro.
- `titulo`: Título do livro.
- `genero`: Gênero literário do livro.
- `exemplares`: Número de exemplares disponíveis na biblioteca.

### Classe `Biblioteca`

Gerencia a coleção de livros da biblioteca e manipula o arquivo JSON como base de dados:
- `carregarLivros()`: Carrega os livros do arquivo JSON `livros.json`.
- `salvarLivros()`: Salva os livros de volta no arquivo JSON `livros.json`.
- `listarLivros()`: Retorna a lista de todos os livros disponíveis na biblioteca.
- `cadastrarLivro(Livro livro)`: Adiciona um novo livro à biblioteca.
- `alugarLivro(String titulo)`: Aluga um livro da biblioteca.
- `devolverLivro(String titulo)`: Devolve um livro à biblioteca.

### Classe `ServidorBiblioteca`

Implementa o servidor que aguarda conexões de clientes e atende às solicitações:
- `iniciar()`: Inicia o servidor e aguarda conexões.
- `atenderCliente(Socket clientSocket)`: Trata as solicitações dos clientes (listagem, cadastro, aluguel, devolução).

### Classe `ClienteBiblioteca`

Implementa o cliente que se comunica com o servidor:
- Permite ao usuário enviar comandos para listar, cadastrar, alugar e devolver livros na biblioteca.

## Como Executar

1. Certifique-se de ter o Java 17 instalado.
2. Compile todas as classes Java usando o comando:
   ```bash
   javac *.java
   ```
3. Inicie o servidor executando a classe `ServidorBiblioteca`:
   ```bash
   java ServidorBiblioteca
   ```
4. Em outro terminal, execute a classe `ClienteBiblioteca` para interagir com o servidor:
   ```bash
   java ClienteBiblioteca
   ```

### Exemplo de Execução

Ao executar o cliente, você poderá utilizar os seguintes comandos:
- `LISTAR`: Lista todos os livros disponíveis na biblioteca.
- `CADASTRAR`: Permite cadastrar um novo livro fornecendo autor, título, gênero e número de exemplares.
- `ALUGAR`: Aluga um livro da biblioteca.
- `DEVOLVER`: Devolve um livro à biblioteca.
- `SAIR`: Encerra a sessão do cliente.

## Arquivo JSON Inicial

O arquivo `livros.json` contém os dados iniciais dos livros na biblioteca e deve ser mantido na raiz do projeto com a estrutura adequada.

## Considerações Finais

Este projeto exemplifica o uso de sockets para comunicação cliente-servidor em Java, com persistência de dados em um arquivo JSON usando a biblioteca org.json. É importante seguir boas práticas de programação e organizar o código de forma clara e eficiente.

---

Certifique-se de ajustar o caminho e o nome do arquivo `livros.json` conforme necessário para garantir que o sistema possa carregar e salvar os dados corretamente.
