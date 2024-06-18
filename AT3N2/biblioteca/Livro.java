package biblioteca;
import java.io.Serializable;

/**
 * Classe que representa um Livro na biblioteca.
 */
public class Livro implements Serializable {
    private String autor;
    private String titulo;
    private String genero;
    private int exemplares;

    /**
     * Construtor da classe Livro.
     *
     * @param autor       Autor do livro.
     * @param titulo      Título do livro.
     * @param genero      Gênero do livro.
     * @param exemplares  Número de exemplares disponíveis.
     */
    public Livro(String autor, String titulo, String genero, int exemplares) {
        this.autor = autor;
        this.titulo = titulo;
        this.genero = genero;
        this.exemplares = exemplares;
    }

    // Getters e Setters
    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public int getExemplares() {
        return exemplares;
    }

    public void setExemplares(int exemplares) {
        this.exemplares = exemplares;
    }

    @Override
    public String toString() {
        return "Livro{" +
                "autor='" + autor + '\'' +
                ", titulo='" + titulo + '\'' +
                ", genero='" + genero + '\'' +
                ", exemplares=" + exemplares +
                '}';
    }
}
