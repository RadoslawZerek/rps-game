package com.radoslawzerek;

/**
 * Author: Radosław Żerek
 */

import java.util.Set;

public final class Paper extends BaseMoves {
    public Paper() {

        name = "Paper";
    }

    @Override
    public Set<BaseMoves> getLosesAgainst() {

        return Set.of(new Scissors(), new Lizard());
    }

    @Override
    public Set<BaseMoves> getWinsWith() {

        return Set.of(new Rock(), new Spock());
    }
}