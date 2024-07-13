package application;

import model.Exceptions.DomainExceptions;
import model.entities.Frame;

import java.util.Scanner;

public class Main {
    public static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        UI.frame = UI.choosePlayers(UI.__init__());

        Character answer = ' ';
        Character winner = ' ';
        do {
            winner = ' ';
            Frame.resetFrame();

            while (UI.frame.checkWinner() == ' ' && Frame.checkWhiteSpaces() != 0) {
                try {
                    UI.frame.getPlayer1().playerMove();
                    winner = UI.frame.checkWinner();
                    if (UI.frame.checkWinner() != ' ' || UI.frame.checkWhiteSpaces() == 0) {
                        break;
                    }

                    UI.frame.getPlayer2().playerMove();
                    winner = UI.frame.checkWinner();
                    if (UI.frame.checkWinner() != ' ' || UI.frame.checkWhiteSpaces() == 0) {
                        break;
                    }
                }
                catch(DomainExceptions e) {
                    System.out.println("Error: " + e.getMessage());
                }
            }

            UI.showFrame();
            UI.showWinner(winner);

            System.out.print("Do you wanna play again? (Y/N) ");
            answer = sc.next().toUpperCase().charAt(0);
        } while (answer == 'Y');

        System.out.println("Game over. Thanks for playing");
        sc.close();
    }
}
