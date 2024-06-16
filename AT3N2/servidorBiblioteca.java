import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

/**
 * Classe que implementa o servidor da biblioteca utilizando sockets.
 */
public class ServidorBiblioteca {
    private static final int PORTA = 12345;
    private Biblioteca biblioteca;

    /**
     * Construtor da classe ServidorBiblioteca.
     */
    public ServidorBiblioteca() {
        biblioteca = new Biblioteca();
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
                    atenderCliente(clientSocket);
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
            String comando = (String) entrada.readObject();
            switch (comando) {
                case "LISTAR":
                    List<Livro> livros = biblioteca.listarLivros();
                    saida.writeObject(livros);
                    break;
                case "CADASTRAR":
                    Livro novoLivro = (Livro) entrada.readObject();
                    biblioteca.cadastrarLivro(novoLivro);
                    saida.writeObject("Livro cadastrado com sucesso!");
                    break;
                case "ALUGAR":
                    String tituloAlugar = (String) entrada.readObject();
                    boolean sucessoAlugar = biblioteca.alugarLivro(tituloAlugar);
                    saida.writeObject(sucessoAlugar ? "Livro alugado com sucesso!" : "Livro não disponível para aluguel.");
                    break;
                case "DEVOLVER":
                    String tituloDevolver = (String) entrada.readObject();
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
