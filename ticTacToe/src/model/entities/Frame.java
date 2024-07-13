package model.entities;

public final class Frame {
    private static Character[][] frame = {
            {' ', ' ', ' '},
            {' ', ' ', ' '},
            {' ', ' ', ' '}
    };

    private static Player player1;
    private static Player player2;

    public Frame(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
    }

    public static Character getFrame(int row, int collumn) {
        return frame[row][collumn];
    }

    public static void setFrame(int row, int collumn, Character user) {
        frame[row][collumn] = user;
    }

    public static Player getPlayer1() {
        return player1;
    }

    public static Player getPlayer2() {
        return player2;
    }

    public static void resetFrame() {
        for (int i = 0; i < frame.length; i++) {
            for (int j = 0; j < frame.length; j++) {
                frame[i][j] = ' ';
            }
        }
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
                return frame[i][0].charValue();
            }
        }

        // COLLUMN VERIFICATION
        for (int i = 0; i < 3; i++) {
            if (frame[0][i] == frame[1][i] && frame[0][i] == frame[2][i]) {
                return frame[0][i].charValue();
            }
        }

        // DIAGONAL VERIFICATION
        if (frame[0][0] == frame[1][1] && frame[0][0] == frame[2][2]) {
            return frame[0][0].charValue();
        }
        if (frame[0][2] == frame[1][1] && frame[0][2] == frame[2][0]) {
            return frame[0][2].charValue();
        }

        return ' ';
    }
}
