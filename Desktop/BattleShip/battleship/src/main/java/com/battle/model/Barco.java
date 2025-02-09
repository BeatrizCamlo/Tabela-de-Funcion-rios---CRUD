package com.battle.model;

import com.battle.repositorio.CoordenadasDoBarco;

public class Barco {
    public enum Posicao {
        VERTICAL, HORIZONTAL, NONE
    }
    private int tamanho;
    private CoordenadasDoBarco coordenadasDoBarco;
    private int quantidade;
    private char caractere;
    private Posicao posicao;

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public char getCaractere() {
        return caractere;
    }

    public void setCaractere(char caractere) {
        this.caractere = caractere;
    }

    public int getTamanho() {
        return tamanho;
    }

    public void setTamanho(int tamanho) {
        this.tamanho = tamanho;
    }

    public CoordenadasDoBarco getCoordenadasDoBarco() {
        return coordenadasDoBarco;
    }

    public void setCoordenadasDoBarco(CoordenadasDoBarco coordenadasDoBarco) {
        this.coordenadasDoBarco = coordenadasDoBarco;
    }

    public Posicao getPosicao() {
        return posicao;
    }

    public void setPosicao(Posicao posicao) {
        this.posicao = posicao;
    }

    public Barco(int tamanho, int quantidade, char caractere) {
        this.tamanho = tamanho;
        this.quantidade = quantidade;
        this.caractere = caractere;
        Posicao posicao = Posicao.NONE;
    }
}
