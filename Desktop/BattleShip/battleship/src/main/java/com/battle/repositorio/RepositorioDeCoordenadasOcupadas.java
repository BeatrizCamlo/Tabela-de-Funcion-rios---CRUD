package com.battle.repositorio;

import java.util.ArrayList;
import java.util.List;

public class RepositorioDeCoordenadasOcupadas {
    private List<Coordenadas> coordenadasOcupadas;

    public RepositorioDeCoordenadasOcupadas() {
        coordenadasOcupadas = new ArrayList<>();
    }

    public void inserir(Coordenadas coordenadas) {
        coordenadasOcupadas.add(coordenadas);
    }

}
