package model.entities;

import application.UI;

import java.util.Random;

public final class Pc extends Player{
    public Pc(Character pc) {
        super(pc);
    }

    @Override
    public void playerMove() {
        Random generator = new Random();
        int row, collumn;

        if (Frame.checkWhiteSpaces() > 0) {
            do {
                row = generator.nextInt(3);
                collumn = generator.nextInt(3);
            } while (Frame.getFrame(row, collumn) != ' ');

            Frame.setFrame((row), (collumn), getPlayer());
        }
        else {
            UI.showWinner(' ');
        }
    }
}
