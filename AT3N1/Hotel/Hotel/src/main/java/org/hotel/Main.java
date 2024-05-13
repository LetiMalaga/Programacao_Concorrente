package org.hotel;

public class Main {
    public static void main(String[] args) {
        Hotel hotel = new Hotel();

        // Começa as threads para hóspedes
        for (Hospede hospede : hotel.getHospedes()) {
            Thread t = new Thread(hospede);
            t.start();
        }

        // Começa as threads para recepcionistas
        for (Receptionista receptionista : hotel.getReceptionistas()) {
            Thread t = new Thread(receptionista);
            t.start();
        }

        // Começa as threads para camareiras
        for (Camareira camareira : hotel.getCamareiras()) {
            Thread t = new Thread(camareira);
            t.start();
        }
    }
}
