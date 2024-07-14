package model.entities;

public abstract class Player {
    private final Character player;

    public Player(Character player) {
        this.player = player;
    }

    public final Character getPlayer() {
        return player;
    }

    abstract public void playerMove();
}
