package application;

import model.Exceptions.DomainExceptions;
import model.entities.Frame;

import java.util.Scanner;

public class Main {
    public static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        UI.frame = UI.choosePlayers(UI.__init__());

        do {
            UI.reset();
            Frame.resetFrame();

            while (Frame.checkWinner() == ' ' && Frame.checkWhiteSpaces() != 0) {
                try {
                    Frame.getPlayer1().playerMove();
                    UI.winner = Frame.checkWinner();
                    if (Frame.checkWinner() != ' ' || Frame.checkWhiteSpaces() == 0) {
                        break;
                    }

                    Frame.getPlayer1().playerMove();
                    UI.winner = Frame.checkWinner();
                    if (Frame.checkWinner() != ' ' || Frame.checkWhiteSpaces() == 0) {
                        break;
                    }
                }
                catch(DomainExceptions e) {
                    System.out.println("Error: " + e.getMessage());
                }
            }

            UI.showFrame();
            UI.showWinner(UI.winner);

            UI.playAgain();
        } while (UI.answer == 'Y');

        sc.close();
    }
}
