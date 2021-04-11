package com.radoslawzerek;

/**
 * Author: Radosław Żerek
 */

import java.util.Set;

public final class Rock extends BaseMoves {
    public Rock() {

        name = "Rock";
    }

    @Override
    public Set<BaseMoves> getLosesAgainst() {
        return Set.of(new Paper(), new Spock());
    }

    @Override
    public Set<BaseMoves> getWinsWith() {

        return Set.of(new Scissors(), new Lizard());
    }
}