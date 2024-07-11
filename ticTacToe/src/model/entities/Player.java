package model.entities;

import application.Main;
import model.DomainExceptions;

public class Player {
    private final Character player = 'X';

    public Character getPlayer() {
        return player;
    }

    public void playerMove() throws DomainExceptions {
        int row, collumn;

        do {
            System.out.print("Choose the row #(1-3): ");
            row = Main.sc.nextInt();
            System.out.print("Choose the collumn #(1-3): ");
            collumn = Main.sc.nextInt();

            if(row <= 0 || row > 3 || collumn <= 0 || collumn > 3) {
                throw new DomainExceptions("Error: You must be choose between 1 to 3.");
            }

            if (Frame.getFrame(row - 1, collumn - 1) != ' ') {
                System.out.println("Invalid moviment.");
            } else {
                Frame.setFrame((row - 1), (collumn - 1), getPlayer());
                break;
            }
        } while (Frame.getFrame(row - 1, collumn - 1) != ' ');
    }
}
