package com.radoslawzerek;

/**
 * Author: Radosław Żerek
 */

import java.util.Set;

public final class Lizard extends BaseMoves {
    public Lizard() {

        name = "Lizard";
    }

    @Override
    public Set<BaseMoves> getLosesAgainst() {
        return Set.of(new Scissors(),new Rock());
    }

    @Override
    public Set<BaseMoves> getWinsWith() {

        return Set.of(new Paper(), new Spock());
    }
}