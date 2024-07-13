package model.entities;

import model.Exceptions.DomainExceptions;

public abstract class Player {
    private Character player;

    public Player(Character player) {
        this.player = player;
    }

    public Character getPlayer() {
        return player;
    }

    abstract public void playerMove() throws DomainExceptions;
}
