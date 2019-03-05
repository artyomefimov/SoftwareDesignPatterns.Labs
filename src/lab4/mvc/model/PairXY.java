package lab4.mvc.model;

import java.util.Objects;

public class PairXY<X, Y> {
    public X x;
    public Y y;

    public static <X, Y> PairXY<X, Y> create(X x, Y y) {
        return new PairXY<>(x, y);
    }

    public PairXY(X x, Y y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof PairXY)) {
            return false;
        }
        PairXY<?, ?> p = (PairXY<?, ?>) o;
        return Objects.equals(p.x, x) && Objects.equals(p.y, y);
    }

    @Override
    public int hashCode() {
        return (x == null ? 0 : x.hashCode()) ^ (y == null ? 0 : y.hashCode());
    }

    @Override
    public String toString() {
        return "PairXY{" + x + " " + y + "}";
    }
}

