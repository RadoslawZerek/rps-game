package com.radoslawzerek;

/**
 * Author: Radosław Żerek
 */

import java.util.Set;

public final class Scissors extends BaseMoves {
    public Scissors() {
        name = "Scissors";
    }

    @Override
    public Set<BaseMoves> getLosesAgainst() {
        return Set.of(new Rock(), new Spock());
    }

    @Override
    public Set<BaseMoves> getWinsWith() {
        return Set.of(new Paper(), new Lizard());
    }
}