package org.hotel;

import java.util.Random;

public class Receptionista implements Runnable {
    private final Hotel hotel;

    public Receptionista(Hotel hotel) {
        this.hotel = hotel;
    }

    @Override
    public void run() {
        Random random = new Random();
        try {
            while (true) {
                // Tempo de serviço randômico
                Thread.sleep(random.nextInt(5000));
                synchronized (hotel.getRecepcaoLock()) {
                    Quarto quarto = hotel.getQuartoDisponivel();
                    if (quarto != null) {
                        quarto.ocupar();
                        // aparentemente não está precisando chegar aqui para alocar um quarto... TODO: Consertar isso
                        System.out.println("Recepcionista alocou um quarto para o novo hóspede.");
                    } else {
                        System.out.println("Recepcionista não conseguiu encontrar um quarto disponível para um novo hóspede.");
                    }
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

