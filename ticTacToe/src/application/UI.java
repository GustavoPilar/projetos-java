package application;

import model.entities.Frame;
import model.entities.Pc;
import model.entities.User;

public class UI {

    static Frame frame;
    static Character winner = ' ';
    static Character answer = ' ';

    public static Frame choosePlayers(int choose) {
        if (choose == 1) {
            System.out.println("Player 1: 'X'");
            System.out.println("Player 2: 'O'");
            return new Frame(new User('X'), new User('O'));
        }
        else {
            System.out.println("Player 1: 'X'");
            System.out.println("PC: 'O'");
            return new Frame(new User('X'), new Pc('O'));
        }
    }

    public static int __init__() {
        int choose;
        System.out.println("|-------------------------------------|");
        System.out.println("|    WELCOME TO TIC TAC TOE GAME #    |");
        System.out.println("|-------------------------------------|");
        System.out.println("|      [1] - Player vs Players        |");
        System.out.println("|      [2] - Player vs Computador     |");
        System.out.println("|-------------------------------------|");
        System.out.print("Type the option: ");

        do {
            choose = Main.sc.nextInt();
            if(choose > 2 || choose < 1) {
                System.out.print("Invalid value. Choose again: ");
            }
        } while (choose != 1 && choose != 2);

        return choose;
    }

    public static void showFrame() {
        System.out.println();
        System.out.printf("1  %c | %c | %c \n", Frame.getFrame(0, 0), Frame.getFrame(0, 1), Frame.getFrame(0, 2));
        System.out.print("  ---|---|---\n");
        System.out.printf("2  %c | %c | %c \n", Frame.getFrame(1, 0), Frame.getFrame(1, 1), Frame.getFrame(1, 2));
        System.out.print("  ---|---|---\n");
        System.out.printf("3  %c | %c | %c \n", Frame.getFrame(2, 0), Frame.getFrame(2, 1), Frame.getFrame(2, 2));
        System.out.println("   1   2   3  ");
    }

    public static void showWinner(Character winner) {
        if (winner.equals(Frame.getPlayer1().getPlayer())) {
            System.out.println("'X' WINS!");
        } else if (winner.equals(Frame.getPlayer2().getPlayer())) {
            System.out.println("'O' WINS!");
        } else {
            System.out.println("TIE.");
        }
    }

    public static void playAgain() {
        System.out.print("Do you wanna play again? (Y/N) ");
        answer = Main.sc.next().toUpperCase().charAt(0);
        if(answer != 'Y') {
            System.out.println("Game over. Thanks for playing. ");
        }
    }

    public static void reset() {
        winner = ' ';
        answer = ' ';
    }
}
