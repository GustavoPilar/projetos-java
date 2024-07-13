package model.entities;

import application.Main;
import application.UI;
import model.Exceptions.DomainExceptions;

public final class User extends Player {
    public User(Character player) {
        super(player);
    }

    @Override
    public void playerMove() throws DomainExceptions {
        int row, collumn;
        do {
            UI.showFrame();

            System.out.print("Choose the row #(1-3): ");
            row = Main.sc.nextInt();
            System.out.print("Choose the collumn #(1-3): ");
            collumn = Main.sc.nextInt();

            if (row > 3 || row <= 0 || collumn > 3 || collumn <= 0) {
                throw new DomainExceptions("You must be choose between 1-3.");
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
