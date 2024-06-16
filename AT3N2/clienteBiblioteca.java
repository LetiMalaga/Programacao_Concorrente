import java.io.*;
import java.net.Socket;
import java.util.List;
import java.util.Scanner;

/**
 * Classe que implementa o cliente da biblioteca que se comunica com o servidor.
 */
public class ClienteBiblioteca {
    private static final String HOST = "localhost";
    private static final int PORTA = 12345;

    /**
     * Método principal para iniciar o cliente.
     *
     * @param args Argumentos da linha de comando.
     */
    public static void main(String[] args) {
        try (Socket socket = new Socket(HOST, PORTA)) {
            ObjectOutputStream saida = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream entrada = new ObjectInputStream(socket.getInputStream());
            Scanner scanner = new Scanner(System.in);

            while (true) {
                System.out.println("Digite o comando (LISTAR, CADASTRAR, ALUGAR, DEVOLVER, SAIR): ");
                String comando = scanner.nextLine().toUpperCase();

                if (comando.equals("SAIR")) {
                    break;
                }

                saida.writeObject(comando);

                switch (comando) {
                    case "LISTAR":
                        List<Livro> livros = (List<Livro>) entrada.readObject();
                        livros.forEach(System.out::println); // Exibe a lista de livros
                        break;
                    case "CADASTRAR":
                        System.out.print("Autor: ");
                        String autor = scanner.nextLine();
                        System.out.print("Título: ");
                        String titulo = scanner.nextLine();
                        System.out.print("Gênero: ");
                        String genero = scanner.nextLine();
                        System.out.print("Número de Exemplares: ");
                        int exemplares = scanner.nextInt();
                        scanner.nextLine();  // Consumir o '\n'
                        Livro livro = new Livro(autor, titulo, genero, exemplares);
                        saida.writeObject(livro); // Envia o novo livro para o servidor
                        System.out.println((String) entrada.readObject());
                        break;
                    case "ALUGAR":
                        System.out.print("Título do livro para alugar: ");
                        String tituloAlugar = scanner.nextLine();
                        saida.writeObject(tituloAlugar); // Envia o título do livro para alugar
                        System.out.println((String) entrada.readObject());
                        break;
                    case "DEVOLVER":
                        System.out.print("Título do livro para devolver: ");
                        String tituloDevolver = scanner.nextLine();
                        saida.writeObject(tituloDevolver); // Envia o título do livro para devolver
                        System.out.println((String) entrada.readObject());
                        break;
                    default:
                        System.out.println((String) entrada.readObject());
                        break;
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
