package com.mygdx.catan;

import java.util.Objects;

public class Coord {
    private final float x;
    private final float y;

    public Coord(float x, float y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Coord that = (Coord) obj;
        return Float.compare(that.x, x) == 0 &&
                Float.compare(that.y, y) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}

