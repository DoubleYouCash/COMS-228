package edu.iastate.cs228.hw1;

public class Streamer extends TownCell {

    public Streamer(Town p, int r, int c) {
        super(p, r, c);
    }

    /**
     * Gets the identity of the cell.
     *
     * @return State
     */
    @Override
    public State who() {
        return State.STREAMER;
    }

    /**
     * Determines the cell type in the next cycle.
     *
     * @param tNew : town of the next cycle
     * @return TownCell
     */
    @Override
    public TownCell next(Town tNew) {
        return null;
    }
}
