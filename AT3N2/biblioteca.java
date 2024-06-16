import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

/**
 * Classe que gerencia a biblioteca e a manipulação do arquivo JSON.
 */
public class Biblioteca {
    private static final String ARQUIVO_JSON = "livros.json"; // Nome do arquivo JSON
    private List<Livro> livros; // Lista de livros da biblioteca

    /**
     * Construtor da classe Biblioteca.
     */
    public Biblioteca() {
        this.livros = carregarLivros(); // Carrega os livros do arquivo JSON
    }

    /**
     * Carrega os livros do arquivo JSON.
     *
     * @return Lista de livros carregada do arquivo JSON.
     */
    private List<Livro> carregarLivros() {
        try (FileReader reader = new FileReader(ARQUIVO_JSON)) {
            Type bibliotecaType = new TypeToken<BibliotecaWrapper>(){}.getType();
            BibliotecaWrapper wrapper = new Gson().fromJson(reader, bibliotecaType);
            return wrapper.getLivros(); // Retorna a lista de livros do wrapper
        } catch (IOException e) {
            e.printStackTrace();
            return null; // Retorna null em caso de erro
        }
    }

    /**
     * Salva os livros no arquivo JSON.
     */
    private void salvarLivros() {
        try (FileWriter writer = new FileWriter(ARQUIVO_JSON)) {
            BibliotecaWrapper wrapper = new BibliotecaWrapper();
            wrapper.setLivros(livros);
            new Gson().toJson(wrapper, writer); // Converte o wrapper para JSON e salva no arquivo
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Retorna a lista de livros da biblioteca.
     *
     * @return Lista de livros.
     */
    public List<Livro> listarLivros() {
        return livros;
    }

    /**
     * Cadastra um novo livro na biblioteca.
     *
     * @param livro Livro a ser cadastrado.
     */
    public void cadastrarLivro(Livro livro) {
        livros.add(livro);
        salvarLivros(); // Salva a lista de livros atualizada no arquivo JSON
    }

    /**
     * Aluga um livro da biblioteca, decrementando o número de exemplares.
     *
     * @param titulo Título do livro a ser alugado.
     * @return true se o aluguel for bem-sucedido, false caso contrário.
     */
    public boolean alugarLivro(String titulo) {
        for (Livro livro : livros) {
            if (livro.getTitulo().equalsIgnoreCase(titulo) && livro.getExemplares() > 0) {
                livro.setExemplares(livro.getExemplares() - 1);
                salvarLivros(); // Salva a lista de livros atualizada no arquivo JSON
                return true;
            }
        }
        return false;
    }

    /**
     * Devolve um livro para a biblioteca, incrementando o número de exemplares.
     *
     * @param titulo Título do livro a ser devolvido.
     * @return true se a devolução for bem-sucedida, false caso contrário.
     */
    public boolean devolverLivro(String titulo) {
        for (Livro livro : livros) {
            if (livro.getTitulo().equalsIgnoreCase(titulo)) {
                livro.setExemplares(livro.getExemplares() + 1);
                salvarLivros(); // Salva a lista de livros atualizada no arquivo JSON
                return true;
            }
        }
        return false;
    }

    /**
     * Classe interna para encapsular a lista de livros no arquivo JSON.
     */
    private static class BibliotecaWrapper {
        private List<Livro> livros;

        public List<Livro> getLivros() {
            return livros;
        }

        public void setLivros(List<Livro> livros) {
            this.livros = livros;
        }
    }
}
