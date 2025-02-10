package com.battle.table;

import com.battle.model.*;
import com.battle.repositorio.Coordenadas;
import com.battle.repositorio.CoordenadasDoBarco;
import com.battle.repositorio.RepositorioDeCoordenadasOcupadas;
import java.util.Random;
import java.util.Scanner;

public class TableGame {
    private boolean gameOver = false;
    private Scanner sc = new Scanner(System.in);
    private final int largura;
    private final int altura;
    private RepositorioDeCoordenadasOcupadas coordenadasOcupadas;
    private char[][] tabuleiroOculto;
    private char[][] tabuleiro;
    private boolean[][] tabuleiroOcupado;
    private Barco[] barcos;
    private Random random = new Random();

    public int getLargura() {
        return largura;
    }

    public int getAltura() {
        return altura;
    }

    public TableGame(int largura, int altura) {
        this.largura = largura;
        this.altura = altura;
        coordenadasOcupadas = new RepositorioDeCoordenadasOcupadas();
        this.tabuleiroOculto = new char[altura][largura];
        this.tabuleiroOcupado = new boolean[altura][largura];
        this.tabuleiro = new char[altura][largura];
        inicializarTabuleiro();

        barcos = new Barco[]{
                new Encouraçado(), new Encouraçado(),
                new Cruzador(), new Cruzador(),
                new Torpedeiro(), new Torpedeiro(),
                new Submarino(), new Submarino()
        };
    }

    public void inicializarTabuleiro() {
        for (int i = 0; i < altura; i++) {
            for (int j = 0; j < largura; j++) {
                tabuleiroOculto[i][j] = '~';
                tabuleiro[i][j] = '~';
            }
        }
    }

    public void inserirTodosBarcos() {
        for (Barco barco : barcos) {
            boolean barcoInserido = false;
            int tentativas = 0;

            while (!barcoInserido && tentativas < 100) {
                int x = random.nextInt(largura);
                int y = random.nextInt(altura);
                boolean vertical = random.nextBoolean();

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
                //tabuleiroOculto[novoY][novoX] = 'A';
                coordenadasOcupadas.inserir(new Coordenadas(novoX, novoY));
            }
        }
    }

    public void verificarCoordenasEscolhidas(int largura, int altura) {
        switch (tabuleiroOculto[altura][largura]){
            case '~': tabuleiro[altura][largura] = 'X'; break;
            case 'S': tabuleiro[altura][largura] = 'S'; break;
            case 'T': tabuleiro[altura][largura] = 'T'; break;
            case 'C': tabuleiro[altura][largura] = 'C'; break;
            case 'E': tabuleiro[altura][largura] = 'E'; break;
        }
    }

    public void inserirCoordenadas(){
        int altura, largura;

        System.out.print("Insira as coordenadas para abater a frota inimiga: (" + getAltura() + ", " + getLargura() + ")");

        do {
            System.out.print("Altura: ");
            while (!sc.hasNextInt()) {
                System.out.println("Entrada inválida! Digite um número inteiro entre 8 e 20.");
                sc.next();
            }
            altura = sc.nextInt();

            System.out.print("Largura: ");
            while (!sc.hasNextInt()) {
                System.out.println("Entrada inválida! Digite um número inteiro entre 8 e 20.");
                sc.next();
            }
            largura = sc.nextInt();

            if (altura < 0 || altura > getAltura() || largura < 0 || largura > getLargura()) {
                System.out.println("Dimensões inválidas! O tabuleiro deve ter entre 0 e" + getAltura() + " (Altura), e 0 e" + getLargura() + " (Largura)");
            }
        }while (altura < 0 || altura > getAltura() || largura < 0 || largura > getLargura());

        verificarCoordenasEscolhidas(altura, largura);
    }

    public boolean existeBarcosNaoEncontrados(){
        boolean existe = false;


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
                System.out.print(tabuleiro[i][j] + " ");
            }
            System.out.println();
        }
    }


}
