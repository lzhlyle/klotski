package com.lzhlyle.klotski.vo;

import java.util.Objects;

public class Position {
    private int abscissa;
    private int ordinate;

    public Position(int abscissa, int ordinate) {
        this.abscissa = abscissa;
        this.ordinate = ordinate;
    }

    public int getAbscissa() {
        return abscissa;
    }

    public int getOrdinate() {
        return ordinate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return abscissa == position.abscissa &&
                ordinate == position.ordinate;
    }

    @Override
    public int hashCode() {
        return Objects.hash(abscissa, ordinate);
    }

    @Override
    public String toString() {
        return "Position{" +
                "abscissa=" + abscissa +
                ", ordinate=" + ordinate +
                '}';
    }
}
