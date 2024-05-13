package org.hotel;

public class Camareira implements Runnable {
    private final Hotel hotel;

    public Camareira(Hotel hotel) {
        this.hotel = hotel;
    }

    @Override
    public void run() {
        while (true) {
            // Para cada quarto, confere se está disponível e limpo. Caso não esteja, limpa o quarto e imprime a mensagem de que o quarto x foi limpo
            for (Quarto quarto : hotel.quartos) {
                synchronized (hotel.getRecepcaoLock()) {
                    if (!quarto.estaDisponivel() && !quarto.limpo) {
                        quarto.limpar();
                        quarto.estaLimpo();
                        System.out.println("\nCamareira limpou o quarto " + quarto.getNumero());
                    }
                }
            }
        }
    }
}

