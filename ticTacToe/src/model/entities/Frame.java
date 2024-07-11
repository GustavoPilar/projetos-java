package model.entities;

public class Frame {
    private static Character[][] frame = {
            {' ', ' ', ' '},
            {' ', ' ', ' '},
            {' ', ' ', ' '}
    };

    private static Player player;
    private static Opponent opponent;

    public Frame(Player player, Opponent opponent) {
        this.opponent = opponent;
        this.player = player;
    }

    public static Character getFrame(int row, int collumn) {
        return frame[row][collumn];
    }

    public static void setFrame(int row, int collumn, Character xo) {
        frame[row][collumn] = xo;
    }

    public void resetFrame() {
        for (int i = 0; i < frame.length; i++) {
            for (int j = 0; j < frame.length; j++) {
                frame[i][j] = ' ';
            }
        }
    }

    public void showFrame() {
        System.out.println();
        System.out.printf(" %c | %c | %c \n", frame[0][0], frame[0][1], frame[0][2]);
        System.out.print("---|---|---\n");
        System.out.printf(" %c | %c | %c \n", frame[1][0], frame[1][1], frame[1][2]);
        System.out.print("---|---|---\n");
        System.out.printf(" %c | %c | %c \n", frame[2][0], frame[2][1], frame[2][2]);
        System.out.println();
    }

    public static int checkWhiteSpaces() {
        int whiteSpaces = 9;
        for (int i = 0; i < frame.length; i++) {
            for (int j = 0; j < frame.length; j++) {
                if (frame[i][j] != ' ') {
                    whiteSpaces--;
                }
            }
        }
        return whiteSpaces;
    }

    public static Character checkWinner() {
        // ROWS VERIFICATION
        for (int i = 0; i < 3; i++) {
            if (frame[i][0] == frame[i][1] && frame[i][0] == frame[i][2]) {
                return frame[i][0];
            }
        }

        // COLLUMN VERIFICATION
        for (int i = 0; i < 3; i++) {
            if (frame[0][i] == frame[1][i] && frame[0][i] == frame[2][i]) {
                return frame[0][i];
            }
        }

        // DIAGONAL VERIFICATION
        if (frame[0][0] == frame[1][1] && frame[0][0] == frame[2][2]) {
            return frame[0][0];
        }
        if (frame[0][2] == frame[1][1] && frame[0][2] == frame[2][0]) {
            return frame[0][2];
        }

        return ' ';
    }

    public static void showWinner(Character winner) {
        if (checkWinner() == player.getPlayer()) {
            System.out.println("YOU WIN!! :D");
        } else if (checkWinner() == opponent.getOpponent()) {
            System.out.println("YOU LOSE! :C");
        } else {
            System.out.println("TIE. :|");
        }
    }
}
