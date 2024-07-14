package model.entities;

import application.Main;
import application.UI;

public final class User extends Player {
    public User(Character player) {
        super(player);
    }

    @Override
    public void playerMove()  {
        int row, collumn;
        do {
            UI.showFrame();

            System.out.print("Player '" + getPlayer() + "' choose the row #(1-3): ");
            row = Main.sc.nextInt();
            System.out.print("Choose the collumn #(1-3): ");
            collumn = Main.sc.nextInt();

            if (Frame.getFrame(row - 1, collumn - 1) != ' ') {
                System.out.println("Invalid position. Choose again...");
            } else {
                Frame.setFrame((row - 1), (collumn - 1), getPlayer());
                break;
            }
        } while (Frame.getFrame(row - 1, collumn - 1) != ' ');
    }

}
