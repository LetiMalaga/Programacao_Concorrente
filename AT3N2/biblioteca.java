import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

public class Biblioteca {
    private static final String ARQUIVO_JSON = "biblioteca.json";
    private List<Livro> livros;

    public Biblioteca() {
        this.livros = carregarLivros();
    }

    private List<Livro> carregarLivros() {
        try (FileReader reader = new FileReader(ARQUIVO_JSON)) {
            Type bibliotecaType = new TypeToken<BibliotecaWrapper>(){}.getType();
            BibliotecaWrapper wrapper = new Gson().fromJson(reader, bibliotecaType);
            return wrapper.getLivros();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private void salvarLivros() {
        try (FileWriter writer = new FileWriter(ARQUIVO_JSON)) {
            BibliotecaWrapper wrapper = new BibliotecaWrapper();
            wrapper.setLivros(livros);
            new Gson().toJson(wrapper, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Livro> listarLivros() {
        return livros;
    }

    public void cadastrarLivro(Livro livro) {
        livros.add(livro);
        salvarLivros();
    }

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
