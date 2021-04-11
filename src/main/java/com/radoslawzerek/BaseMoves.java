package com.radoslawzerek;

/**
 * Author: Radosław Żerek
 */

import java.util.Objects;
import java.util.Set;

public abstract class BaseMoves {
    protected String name;

    public String getName() {

        return name;
    }

    public abstract Set<BaseMoves> getWinsWith();

    public abstract Set<BaseMoves> getLosesAgainst();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BaseMoves baseMove = (BaseMoves) o;
        return Objects.equals(name, baseMove.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}