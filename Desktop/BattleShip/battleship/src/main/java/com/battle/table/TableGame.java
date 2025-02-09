package com.battle.table;

import com.battle.model.*;
import com.battle.repositorio.Coordenadas;
import com.battle.repositorio.CoordenadasDoBarco;
import com.battle.repositorio.RepositorioDeCoordenadasOcupadas;
import java.util.Random;

public class TableGame {
    private int largura;
    private int altura;
    private RepositorioDeCoordenadasOcupadas coordenadasOcupadas;
    private char[][] tabuleiroOculto;
    private boolean[][] tabuleiroOcupado;
    private Barco[] barcos;
    private Random random = new Random();

    public TableGame(int largura, int altura) {
        this.largura = largura;
        this.altura = altura;
        coordenadasOcupadas = new RepositorioDeCoordenadasOcupadas();
        this.tabuleiroOculto = new char[altura][largura];
        this.tabuleiroOcupado = new boolean[altura][largura];
        inicializarTabuleiro();

        barcos = new Barco[]{
                new Encouraçado(), new Encouraçado(),
                new Cruzador(), new Cruzador(),
                new Torpedeiro(), new Torpedeiro(),
                new Submarino(), new Submarino()
        };
    }

    private void inicializarTabuleiro() {
        for (int i = 0; i < altura; i++) {
            for (int j = 0; j < largura; j++) {
                tabuleiroOculto[i][j] = '~';
            }
        }
    }

    public void inserirTodosBarcos() {
        for (Barco barco : barcos) {
            boolean barcoInserido = false;
            int tentativas = 0;

            while (!barcoInserido && tentativas < 100) { // Evita loop infinito
                int x = random.nextInt(largura);
                int y = random.nextInt(altura);
                boolean vertical = random.nextBoolean(); // Aleatoriza a orientação do barco

                if (podeInserirBarco(x, y, barco, vertical)) {
                    inserirBarcoNoTabuleiro(x, y, barco, vertical);
                    barco.setPosicao(vertical ? Barco.Posicao.VERTICAL : Barco.Posicao.HORIZONTAL);
                    barcoInserido = true;
                }
                tentativas++;
            }

            if (!barcoInserido) {
                System.out.println("Não foi possível inserir um barco após várias tentativas.");
            }
        }
    }

    private boolean podeInserirBarco(int x, int y, Barco barco, boolean vertical) {
        if (vertical) {
            if (y + barco.getTamanho() > altura) return false;
            for (int k = -1; k <= barco.getTamanho(); k++) {
                int novoY = y + k;
                if (novoY >= 0 && novoY < altura && tabuleiroOcupado[novoY][x]) return false;
            }
        } else {
            if (x + barco.getTamanho() > largura) return false;
            for (int k = -1; k <= barco.getTamanho(); k++) {
                int novoX = x + k;
                if (novoX >= 0 && novoX < largura && tabuleiroOcupado[y][novoX]) return false;
            }
        }
        return true;
    }

    private void inserirBarcoNoTabuleiro(int x, int y, Barco barco, boolean vertical) {
        for (int k = 0; k < barco.getTamanho(); k++) {
            int novoX = vertical ? x : x + k;
            int novoY = vertical ? y + k : y;

            Coordenadas coordenadas = new Coordenadas(novoX, novoY);
            CoordenadasDoBarco coordenadasDoBarco = new CoordenadasDoBarco();
            coordenadasDoBarco.inserirCoordenadasDoBarco(coordenadas);
            coordenadasOcupadas.inserir(coordenadas);

            tabuleiroOcupado[novoY][novoX] = true;
            tabuleiroOculto[novoY][novoX] = barco.getCaractere();
            inserirAguaAoRedor(novoX, novoY);
        }
        System.out.println("Barco inserido com sucesso!");
    }

    private void inserirAguaAoRedor(int x, int y) {
        int[][] direcoes = {
                {-1, -1}, {-1, 0}, {-1, 1},
                {0, -1},           {0, 1},
                {1, -1}, {1, 0}, {1, 1}
        };

        for (int[] direcao : direcoes) {
            int novoX = x + direcao[0];
            int novoY = y + direcao[1];

            if (novoX >= 0 && novoX < largura && novoY >= 0 && novoY < altura && !tabuleiroOcupado[novoY][novoX]) {
                tabuleiroOcupado[novoY][novoX] = true;
                tabuleiroOculto[novoY][novoX] = 'A';
                coordenadasOcupadas.inserir(new Coordenadas(novoX, novoY));
            }
        }
    }

    public void exibirTabuleiro() {
        System.out.print("  ");
        for (int i = 0; i < largura; i++) {
            System.out.print(i + " ");
        }
        System.out.println();

        for (int i = 0; i < altura; i++) {
            System.out.print(i + " ");
            for (int j = 0; j < largura; j++) {
                System.out.print(tabuleiroOculto[i][j] + " ");
            }
            System.out.println();
        }
    }
}
