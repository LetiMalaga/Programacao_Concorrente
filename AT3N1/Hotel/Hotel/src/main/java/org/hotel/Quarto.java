package org.hotel;

public class Quarto {
    private final int numero;
    private boolean ocupado;
    public boolean limpo;

    public Quarto(int numero) {
        this.numero = numero;
        this.ocupado = false;
        this.limpo = true;
    }

    // Método para conferir se o quarto está disponível para ser ocupado
    public synchronized boolean estaDisponivel() {
        return !ocupado && !limpo;
    }

    public synchronized boolean estaLimpo() {
        return !ocupado && limpo;
    }

    // Método para ocupar o quarto
    public synchronized void ocupar() {
        ocupado = true;
        limpo = false;
    }

    // Método para liberar o quarto
    public synchronized void liberar() {
        ocupado = false;
        limpo = false;
    }

    // Método para limpar o quarto
    public synchronized void limpar() {
        limpo = true;
    }

    public int getNumero() {
        return numero;
    }
}
