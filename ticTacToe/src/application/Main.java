package application;

import model.DomainExceptions;
import model.entities.Frame;
import model.entities.Opponent;
import model.entities.Player;

import java.util.Scanner;

public class Main {
    public static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        Player player = new Player();
        Opponent opponent = new Opponent();
        Frame frame = new Frame(player, opponent);

        Character winner = ' ';
        Character answer = ' ';

        System.out.println("WELCOME TO THE GAME TIC TAC TOE #");
        do {
            winner = ' ';
            frame.resetFrame();

            while (frame.checkWinner() == ' ' && frame.checkWhiteSpaces() != 0) {
                try {
                    frame.showFrame();

                    player.playerMove();
                    winner = frame.checkWinner();
                    if (frame.checkWinner() != ' ' || frame.checkWhiteSpaces() == 0) {
                        break;
                    }

                    opponent.oppenentMove();
                    winner = frame.checkWinner();
                    if (frame.checkWinner() != ' ' || frame.checkWhiteSpaces() == 0) {
                        break;
                    }
                }
                catch(DomainExceptions e) {
                    System.out.println(e.getMessage());
                }
                catch (RuntimeException e) {
                    System.out.println("Unexpected error: " + e.getMessage());
                };
            }

            frame.showFrame();
            frame.showWinner(winner);

            System.out.print("Do you wanna play again? (Y/N) ");
            answer = sc.next().toUpperCase().charAt(0);
        } while (answer == 'Y');

        System.out.println("Game over. Thanks for playing");
        sc.close();
    }
}
