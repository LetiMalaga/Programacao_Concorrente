import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

/**
 * Classe que implementa o servidor da biblioteca utilizando sockets.
 */
public class ServidorBiblioteca {
    private static final int PORTA = 12345; // Porta na qual o servidor vai escutar
    private Biblioteca biblioteca;

    /**
     * Construtor da classe ServidorBiblioteca.
     */
    public ServidorBiblioteca() {
        biblioteca = new Biblioteca(); // Inicializa a biblioteca
    }

    /**
     * Inicia o servidor e aguarda conexões de clientes.
     */
    public void iniciar() {
        try (ServerSocket serverSocket = new ServerSocket(PORTA)) {
            System.out.println("Servidor iniciado na porta " + PORTA);
            while (true) {
                try (Socket clientSocket = serverSocket.accept()) {
                    System.out.println("Cliente conectado: " + clientSocket.getInetAddress());
                    atenderCliente(clientSocket); // Trata a conexão com o cliente
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Trata as solicitações dos clientes.
     *
     * @param clientSocket Socket do cliente.
     */
    private void atenderCliente(Socket clientSocket) {
        try (
            ObjectInputStream entrada = new ObjectInputStream(clientSocket.getInputStream());
            ObjectOutputStream saida = new ObjectOutputStream(clientSocket.getOutputStream())
        ) {
            String comando = (String) entrada.readObject(); // Lê o comando enviado pelo cliente
            switch (comando) {
                case "LISTAR":
                    List<Livro> livros = biblioteca.listarLivros();
                    saida.writeObject(livros); // Envia a lista de livros para o cliente
                    break;
                case "CADASTRAR":
                    Livro novoLivro = (Livro) entrada.readObject(); // Lê o novo livro do cliente
                    biblioteca.cadastrarLivro(novoLivro);
                    saida.writeObject("Livro cadastrado com sucesso!");
                    break;
                case "ALUGAR":
                    String tituloAlugar = (String) entrada.readObject(); // Lê o título do livro a ser alugado
                    boolean sucessoAlugar = biblioteca.alugarLivro(tituloAlugar);
                    saida.writeObject(sucessoAlugar ? "Livro alugado com sucesso!" : "Livro não disponível para aluguel.");
                    break;
                case "DEVOLVER":
                    String tituloDevolver = (String) entrada.readObject(); // Lê o título do livro a ser devolvido
                    boolean sucessoDevolver = biblioteca.devolverLivro(tituloDevolver);
                    saida.writeObject(sucessoDevolver ? "Livro devolvido com sucesso!" : "Erro ao devolver o livro.");
                    break;
                default:
                    saida.writeObject("Comando desconhecido.");
                    break;
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Método principal para iniciar o servidor.
     *
     * @param args Argumentos da linha de comando.
     */
    public static void main(String[] args) {
        new ServidorBiblioteca().iniciar();
    }
}
