package edu.iastate.cs228.hw1;

public class Outage extends TownCell {

    public Outage(Town p, int r, int c) {
        super(p, r, c);
    }

    /**
     * Gets the identity of the cell.
     *
     * @return State
     */
    @Override
    public State who() {
        return State.OUTAGE;
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
