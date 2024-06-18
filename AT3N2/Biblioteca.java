import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONException; // Importe a exceção JSONException

/**
 * Classe que gerencia a biblioteca e a manipulação do arquivo JSON.
 */
public class Biblioteca {
    private static final String ARQUIVO_JSON = "livros.json";
    private List<Livro> livros;

    /**
     * Construtor da classe Biblioteca.
     */
    public Biblioteca() {
        this.livros = carregarLivros();
    }

    /**
     * Carrega os livros do arquivo JSON.
     *
     * @return Lista de livros carregada do arquivo JSON.
     */
    public List<Livro> carregarLivros() {
        livros = new ArrayList<>();
        try {
            byte[] bytes = Files.readAllBytes(Paths.get(ARQUIVO_JSON));
            String content = new String(bytes);
            JSONObject jsonObject = new JSONObject(content);
            JSONArray livrosArray = jsonObject.getJSONArray("livros");
            for (int i = 0; i < livrosArray.length(); i++) {
                JSONObject livroJson = livrosArray.getJSONObject(i);
                Livro livro = new Livro(
                        livroJson.getString("autor"),
                        livroJson.getString("titulo"),
                        livroJson.getString("genero"),
                        livroJson.getInt("exemplares")
                );
                livros.add(livro);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return livros;
    }

    /**
     * Salva os livros no arquivo JSON.
     */
    private void salvarLivros() {
        try (FileWriter writer = new FileWriter(ARQUIVO_JSON)) {
            JSONObject jsonObject = new JSONObject();
            JSONArray livrosArray = new JSONArray();
            for (Livro livro : livros) {
                JSONObject livroJson = new JSONObject();
                livroJson.put("autor", livro.getAutor());
                livroJson.put("titulo", livro.getTitulo());
                livroJson.put("genero", livro.getGenero());
                livroJson.put("exemplares", livro.getExemplares());
                livrosArray.put(livroJson);
            }
            jsonObject.put("livros", livrosArray);
            writer.write(jsonObject.toString(4)); // Escreve no arquivo com indentação
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
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
        salvarLivros();
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
                salvarLivros();
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
                salvarLivros();
                return true;
            }
        }
        return false;
    }
}
