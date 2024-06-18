import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.List;
import java.util.Scanner;

// Classe que implementa o cliente da biblioteca que se comunica com o servidor.fsw
public class ClienteBiblioteca {
    private static final String HOST = "localhost";
    private static final int PORTA = 12345;

    /**
     * Método principal para iniciar o cliente.
     *
     * @param args Argumentos da linha de comando.
     */
    public static void main(String[] args) {
        // Início do bloco try-with-resources para garantir fechamento automático dos recursos
        try (Socket socket = new Socket(HOST, PORTA); // Abre uma conexão com o servidor na porta especificada
             ObjectOutputStream saida = new ObjectOutputStream(socket.getOutputStream()); // Stream para enviar objetos ao servidor
             ObjectInputStream entrada = new ObjectInputStream(socket.getInputStream()); // Stream para receber objetos do servidor
             Scanner scanner = new Scanner(System.in)) { // Scanner para ler entrada do usuário a partir do console

            // Loop principal do cliente
            while (true) {
                System.out.println("Digite o comando (LISTAR, CADASTRAR, ALUGAR, DEVOLVER, SAIR): ");
                String comando = scanner.nextLine().toUpperCase(); // Lê o comando do usuário e converte para maiúsculas

                if (comando.equals("SAIR")) {
                    break; // Encerra o loop se o usuário digitar "SAIR"
                }

                saida.writeObject(comando); // Envia o comando para o servidor

                // Processa a resposta do servidor de acordo com o comando enviado
                switch (comando) {
                    case "LISTAR":
                        // Recebe a resposta do servidor
                        Object obj = entrada.readObject();
                        // Verifica se a resposta é do tipo esperado (List<Livro>)
                        if (obj instanceof List) {
                            List<Livro> livros = (List<Livro>) obj; // Converte com segurança para List<Livro>
                            livros.forEach(System.out::println); // Exibe cada livro no console
                        } else {
                            System.out.println("Resposta inesperada do servidor.");
                        }
                        break;
                    case "CADASTRAR":
                        System.out.println("Digite os dados do livro:");

                        // Solicita ao usuário os dados para cadastrar um novo livro
                        System.out.print("Autor: ");
                        String autor = scanner.nextLine();
                        System.out.print("Título: ");
                        String titulo = scanner.nextLine();
                        System.out.print("Gênero: ");
                        String genero = scanner.nextLine();
                        System.out.print("Número de Exemplares: ");
                        int exemplares = scanner.nextInt();
                        scanner.nextLine(); // Consumir o '\n'

                        // Cria um novo objeto Livro com os dados informados
                        Livro novoLivro = new Livro(autor, titulo, genero, exemplares);
                        saida.writeObject(novoLivro); // Envia o novo livro para o servidor
                        System.out.println((String) entrada.readObject()); // Exibe a mensagem de confirmação do servidor
                        break;
                    case "ALUGAR":
                        // Solicita ao usuário o título do livro para alugar
                        System.out.print("Título do livro para alugar: ");
                        String tituloAlugar = scanner.nextLine();
                        saida.writeObject(tituloAlugar); // Envia o título do livro para alugar
                        System.out.println((String) entrada.readObject()); // Exibe a mensagem de confirmação do servidor
                        break;
                    case "DEVOLVER":
                        // Solicita ao usuário o título do livro para devolver
                        System.out.print("Título do livro para devolver: ");
                        String tituloDevolver = scanner.nextLine();
                        saida.writeObject(tituloDevolver); // Envia o título do livro para devolver
                        System.out.println((String) entrada.readObject()); // Exibe a mensagem de confirmação do servidor
                        break;
                    default:
                        // Exibe mensagens de resposta do servidor para comandos inválidos ou outros casos
                        System.out.println((String) entrada.readObject());
                        break;
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace(); // Trata exceções de E/S (IOException) ou de classes não encontradas (ClassNotFoundException)
        }
    }
}
