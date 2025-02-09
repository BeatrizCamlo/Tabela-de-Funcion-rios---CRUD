package com.battle.game;

import com.battle.model.*;
import com.battle.table.TableGame;
import java.util.Scanner;

public class GameView {
    private TableGame game;
    private Scanner sc = new Scanner(System.in);

    public GameView() {
        this.game = new TableGame(0,0);
    }

    public void introducao() {
        System.out.println(
                "-------------------------------------------------\n" +
                        "| BEM-VINDO AO PROJETO BATTLESHIP (BATALHA NAVAL) |\n" +
                        "| BY ANA BEATRIZ CAMILO                           |\n" +
                        "-------------------------------------------------\n\n" +
                        "Por favor, insira as medidas de altura e largura para o tabuleiro (8-20):"
        );
    }

    public void entradaTamanhoTabuleiro() {
        int altura, largura;

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

            if (altura < 8 || altura > 20 || largura < 8 || largura > 20) {
                System.out.println("Dimensões inválidas! O tabuleiro deve ter entre 8 e 20 espaços.");
            }

        } while (altura < 8 || altura > 20 || largura < 8 || largura > 20);

        game = new TableGame(altura, largura);
    }

    public void game() {
        game.inserirTodosBarcos();
        game.exibirTabuleiro();

    }




    public static void main(String[] args) {
        GameView janela = new GameView();

        janela.introducao();
        janela.entradaTamanhoTabuleiro();
        janela.game();
    }
}
