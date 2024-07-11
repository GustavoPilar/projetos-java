package model.entities;

import java.util.Random;

public class Opponent {
    private final Character opponent = 'O';

    public Character getOpponent() {
        return opponent;
    }

    public void oppenentMove() {
        Random generator = new Random();
        int row, collumn;

        if (Frame.checkWhiteSpaces() > 0) {
            do {
                row = generator.nextInt(3);
                collumn = generator.nextInt(3);
            } while (Frame.getFrame(row, collumn) != ' ');

            Frame.setFrame((row), (collumn), getOpponent());
        }
        else {
            Frame.showWinner(' ');
        }
    }
}
