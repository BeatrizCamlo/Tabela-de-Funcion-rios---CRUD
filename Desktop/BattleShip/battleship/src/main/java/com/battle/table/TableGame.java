package com.battle.table;

import com.battle.model.*;
import com.battle.repositorio.Coordenadas;
import com.battle.repositorio.CoordenadasDoBarco;
import com.battle.repositorio.RepositorioDeCoordenadasOcupadas;

public class TableGame {
    private int largura;
    private int altura;
    private RepositorioDeCoordenadasOcupadas coordenadasOcupadas;
    private char[][] tabuleiroOculto;
    private boolean[][] tabuleiroOcupado;
    Barco encouracado1;
    Barco encouracado2;
    Barco cruzador1;
    Barco cruzador2;
    Barco contratorpedeiro1;
    Barco contratorpedeiro2;
    Barco submarino1;
    Barco submarino2;

    public TableGame(int largura, int altura) {
        this.largura = largura;
        this.altura = altura;
        coordenadasOcupadas = new RepositorioDeCoordenadasOcupadas();
        this.tabuleiroOculto = new char[altura][largura];
        this.tabuleiroOcupado = new boolean[altura][largura];
        tabuleiroMatrix();

        encouracado1 = new Encouraçado();
        encouracado2 = new Encouraçado();
        cruzador1 = new Cruzador();
        cruzador2 = new Cruzador();
        contratorpedeiro1 = new Torpedeiro();
        contratorpedeiro2 = new Torpedeiro();
        submarino1 = new Submarino();
        submarino2 = new Submarino();
    }

    public void tabuleiroMatrix() {
        for (int i = 0; i < altura; i++) {
            for (int j = 0; j < largura; j++) {
                tabuleiroOculto[i][j] = '~';
            }
        }
    }

    public void inciarTabuleiro() {
        System.out.print(" ");
        for (int i = 0; i < largura + 2; i++) {
            System.out.print("* ");
        }
        System.out.println();
        for (int i = 0; i < altura; i++) {
            System.out.print(" |");
            for (int j = 0; j < largura; j++) {
                System.out.print(" " + tabuleiroOculto[i][j]);
            }
            System.out.print(" |\n");
        }
        System.out.print(" ");
        for (int i = 0; i < largura + 2; i++) {
            System.out.print("* ");
        }
        System.out.println();
    }

    public void inserirBarco(Barco barco) {
        boolean barcoInserido = false;
        for (int i = 0; i < altura; i++) {
            for (int j = 0; j < largura; j++) {
                if (podeInserirBarcoVerfical(j, i, barco)) {
                    for (int k = 0; k < barco.getTamanho(); k++) {
                        Coordenadas coordenadas = new Coordenadas(j, i + k);
                        CoordenadasDoBarco coordenadasDoBarco = new CoordenadasDoBarco();
                        coordenadasOcupadas.inserir(coordenadas);
                        coordenadasDoBarco.inserirCoordenadasDoBarco(coordenadas);
                        tabuleiroOcupado[i + k][j] = true;
                        tabuleiroOculto[i + k][j] = barco.getCaractere();
                        inserirAguaAoRedor(j,i);

                    }
                    System.out.println("Barco inserido com sucesso!");
                    barco.setPosicao(Barco.Posicao.VERTICAL);
                    barcoInserido = true;
                    break;
                }

                if (podeInserirBarcoHorizontal(j, i, barco)) {
                    for (int k = 0; k < barco.getTamanho(); k++) {
                        Coordenadas coordenadas = new Coordenadas(j + k, i);
                        CoordenadasDoBarco coordenadasDoBarco = new CoordenadasDoBarco();
                        coordenadasDoBarco.inserirCoordenadasDoBarco(coordenadas);
                        coordenadasOcupadas.inserir(coordenadas);
                        tabuleiroOcupado[i][j + k] = true;
                        tabuleiroOculto[i][j + k] = barco.getCaractere();
                        inserirAguaAoRedor(j,i);
                    }
                    System.out.println("Barco inserido com sucesso!");
                    barco.setPosicao(Barco.Posicao.HORIZONTAL);
                    barcoInserido = true;
                    break;
                }
            }
            if (barcoInserido) {
                break;
            }
        }
        if (!barcoInserido) {
            System.out.println("Não foi possível inserir o barco!");
        }
    }

    public boolean podeInserirBarcoVerfical(int coordenadaX, int coordenadaY, Barco barco) {
        if (coordenadaY + barco.getTamanho() > altura) {
            return false;
        }
        for (int k = 0; k < barco.getTamanho(); k++) {
            if (tabuleiroOcupado[coordenadaY + k][coordenadaX]) {
                return false;
            }
        }
        return true;
    }

    public boolean podeInserirBarcoHorizontal(int coordenadaX, int coordenadaY, Barco barco) {
        if (coordenadaX + barco.getTamanho() > largura) {
            return false;
        }
        for (int k = 0; k < barco.getTamanho(); k++) {
            if (tabuleiroOcupado[coordenadaY][coordenadaX + k]) {
                return false;
            }
        }

        return true;
    }

    public void tabuleiroComBarcos() {
        for (int i = 0; i < altura; i++) {
            for (int j = 0; j < largura; j++) {
                System.out.print(tabuleiroOculto[i][j] + " ");
            }
            System.out.println();
        }
    }

    public void inserirTodosBarcos() {
        inserirBarco(submarino1);
        inserirBarco(submarino2);
        inserirBarco(contratorpedeiro1);
        inserirBarco(contratorpedeiro2);
        inserirBarco(cruzador1);
        inserirBarco(cruzador2);
        inserirBarco(encouracado1);
        inserirBarco(encouracado2);
    }

    public void inserirAguaAoRedor(int coordenadaX, int coordenadaY) {
        Coordenadas coordenadas;
        int[][] direcoes = {
                {-1, -1}, {-1, 0}, {-1, 1},
                {0, -1},           {0, 1},
                {1, -1}, {1, 0}, {1, 1}
        };

        for (int[] direcao : direcoes) {
            int novoX = coordenadaX + direcao[0];
            int novoY = coordenadaY + direcao[1];

            if (novoX >= 0 && novoX < largura && novoY >= 0 && novoY < altura) {
                tabuleiroOcupado[novoY][novoX] = true;
                tabuleiroOculto[novoY][novoX] = 'A';
                coordenadas = new Coordenadas(novoX, novoY);
                coordenadasOcupadas.inserir(coordenadas);
            }
        }
    }
}


