package org.hotel;

import java.util.ArrayList;
import java.util.List;

public class Hotel {
    // Definição de magic numbers para a quantidade de cada categoria específica
    private static final int NUM_QUARTOS = 10;
    private static final int NUM_HOSPEDES = 50;
    private static final int NUM_CAMAREIRAS = 10;
    private static final int NUM_RECEPTIONISTAS = 5;

    // Listagem das quantidades de cada categoria específica
    public final List<Quarto> quartos = new ArrayList<>();
    private final List<Hospede> hospedes = new ArrayList<>();
    private final List<Camareira> camareiras = new ArrayList<>();
    private final List<Receptionista> receptionistas = new ArrayList<>();

    private final Object recepcaoLock = new Object();

    // Cria o hotel com as especificações definidas
    public Hotel() {
        // Inicializa quartos
        for (int i = 0; i < NUM_QUARTOS; i++) {
            quartos.add(new Quarto(i));
        }

        // Inicializa hóspedes
        for (int i = 0; i < NUM_HOSPEDES; i++) {
            hospedes.add(new Hospede(this, "Guest " + i));
        }

        // Inicializa recepcionistas
        for (int i = 0; i < NUM_RECEPTIONISTAS; i++) {
            receptionistas.add(new Receptionista(this));
        }

        // Inicializa camareiras
        for (int i = 0; i < NUM_CAMAREIRAS; i++) {
            camareiras.add(new Camareira(this));
        }
    }

    // Pega o quarto que está disponível e limpo
    public Quarto getQuartoDisponivel() {
        for (Quarto quarto : quartos) {
            if (quarto.estaLimpo()) {
                quarto.ocupar();
                return quarto;
            }
        }
        return null;
    }

    public Object getRecepcaoLock() {
        return recepcaoLock;
    }

    public List<Hospede> getHospedes() {
        return hospedes;
    }

    public List<Camareira> getCamareiras() {
        return camareiras;
    }

    public List<Receptionista> getReceptionistas() {
        return receptionistas;
    }
}

