package org.hotel;

import java.util.Random;

public class Hospede implements Runnable {
    private final Hotel hotel;
    private final String nome;

    public Hospede(Hotel hotel, String nome) {
        this.hotel = hotel;
        this.nome = nome;
    }

    @Override
    public void run() {
        Random random = new Random();
        try {
            // Implementando o tempo aleatório de entrada de hóspedes
            Thread.sleep(random.nextInt(5000));
            Quarto quarto = hotel.getQuartoDisponivel();
            if (quarto != null) {
                synchronized (hotel.getRecepcaoLock()) {
                    quarto.ocupar();
                    System.out.println("\n" + nome + " ocupou o quarto " + quarto.getNumero());
                }
                // Implementando o tempo aleatório de estadia de hóspedes
                Thread.sleep(random.nextInt(5000));
                synchronized (hotel.getRecepcaoLock()) {
                    quarto.liberar();
                    System.out.println("\n" + nome + " liberou o quarto " + quarto.getNumero());
                }
            } else {
                System.out.println("\n" + nome + " não achou um quarto disponível e liberou o hotel.");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
