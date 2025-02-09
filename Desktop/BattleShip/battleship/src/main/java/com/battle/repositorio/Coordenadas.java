package com.battle.repositorio;

import java.util.Objects;

public class Coordenadas {
    private int coordenadaX;
    private int coordenadaY;

    public Coordenadas(int coordenadaX, int coordenadaY) {
        this.coordenadaX = coordenadaX;
        this.coordenadaY = coordenadaY;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coordenadas that = (Coordenadas) o;
        return coordenadaX == that.coordenadaX && coordenadaY == that.coordenadaY;
    }

    @Override
    public int hashCode() {
        return Objects.hash(coordenadaX, coordenadaY);
    }
}
