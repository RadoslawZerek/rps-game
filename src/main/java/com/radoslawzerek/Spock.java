package com.radoslawzerek;

/**
 * Author: Radosław Żerek
 */

import java.util.Set;

public final class Spock extends BaseMoves {
    public Spock() {

        name = "Spock";
    }

    @Override
    public Set<BaseMoves> getLosesAgainst() {

        return Set.of(new Lizard(), new Paper());
    }

    @Override
    public Set<BaseMoves> getWinsWith() {

        return Set.of(new Rock(), new Scissors());
    }
}