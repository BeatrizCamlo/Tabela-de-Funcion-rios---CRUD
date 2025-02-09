package com.battle.repositorio;

import java.util.ArrayList;

public class CoordenadasDoBarco {

    private ArrayList<Coordenadas> coordenadasDoBarco;

    public CoordenadasDoBarco() {
        coordenadasDoBarco = new ArrayList<>();

    }

    public void inserirCoordenadasDoBarco(Coordenadas coordenadas) {
        coordenadasDoBarco.add(coordenadas);
    }
}
