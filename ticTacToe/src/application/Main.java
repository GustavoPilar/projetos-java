package application;

import model.entities.Frame;

import java.util.Scanner;

public class Main {
    public static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        UI.frame = UI.choosePlayers(UI.__init__());

        do {
            UI.reset();
            Frame.resetFrame();

            while (Frame.checkWinner() == ' ' && Frame.checkWhiteSpaces() != 0) {

                    Frame.getPlayer1().playerMove();
                    UI.winner = Frame.checkWinner();
                    if (Frame.checkWinner() != ' ' || Frame.checkWhiteSpaces() == 0) {
                        break;
                    }

                    Frame.getPlayer2().playerMove();
                    UI.winner = Frame.checkWinner();
                    if (Frame.checkWinner() != ' ' || Frame.checkWhiteSpaces() == 0) {
                        break;
                    }
            }

            UI.showFrame();
            UI.showWinner(UI.winner);

            UI.playAgain();
        } while (UI.answer == 'Y');

        sc.close();
    }
}
