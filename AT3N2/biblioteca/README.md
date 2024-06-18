# Biblioteca Socket Server

Este projeto implementa um servidor de biblioteca utilizando sockets em Java, que permite gerenciar um registro de livros, realizando operações de listagem, cadastro, aluguel e devolução de livros. O servidor e o cliente se comunicam por meio de sockets e os dados são armazenados em um arquivo JSON.

## Funcionalidades

- **Listagem dos livros**: O cliente pode solicitar ao servidor a listagem de todos os livros disponíveis na biblioteca.
- **Cadastro de livros**: O cliente pode cadastrar novos livros na biblioteca.
- **Aluguel de livros**: O cliente pode alugar livros, diminuindo o número de exemplares disponíveis.
- **Devolução de livros**: O cliente pode devolver livros, aumentando o número de exemplares disponíveis.

## Estrutura do Projeto

### Classe `Livro`

Representa um livro na biblioteca, contendo os seguintes atributos:
- `autor`: String
- `nome`: String
- `genero`: String
- `numeroExemplares`: int

### Classe `Biblioteca`

Gerencia a coleção de livros da biblioteca e manipula o arquivo JSON que serve como base de dados:
- `carregarLivros()`: Carrega os livros do arquivo JSON.
- `salvarLivros()`: Salva os livros no arquivo JSON.
- `listarLivros()`: Retorna a lista de livros.
- `cadastrarLivro(Livro livro)`: Adiciona um novo livro à biblioteca.
- `alugarLivro(String nome)`: Aluga um livro, decrementando o número de exemplares.
- `devolverLivro(String nome)`: Devolve um livro, incrementando o número de exemplares.

### Classe `ServidorBiblioteca`

Implementa o servidor utilizando sockets:
- `iniciar()`: Inicia o servidor e aguarda conexões de clientes.
- `atenderCliente(Socket clientSocket)`: Trata as solicitações dos clientes (listagem, cadastro, aluguel, devolução).

### Classe `ClienteBiblioteca`

Implementa o cliente que se comunica com o servidor:
- Permite ao usuário enviar comandos para listar, cadastrar, alugar e devolver livros.

## Como Executar

1. Certifique-se de ter o Java 17 instalado.
2. Compile todas as classes Java.
3. Inicie o servidor executando a classe `ServidorBiblioteca`.
4. Em outro terminal, execute a classe `ClienteBiblioteca` para interagir com o servidor.

### Exemplo de Execução

```bash
# Compile o código
javac -cp gson-2.8.8.jar:. *.java

# Inicie o servidor
java -cp gson-2.8.8.jar:. ServidorBiblioteca

# Em outro terminal, inicie o cliente
java -cp gson-2.8.8.jar:. ClienteBiblioteca
```

### Arquivo JSON Inicial

O arquivo `livros.json` foi entregue pelo professor.

## Considerações Finais

Este projeto visa exemplificar o uso de sockets para comunicação cliente-servidor em Java, com persistência de dados em um arquivo JSON, seguindo boas práticas de programação e organização de código.
