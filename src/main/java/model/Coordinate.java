package model;

public class Coordinate {
    private final int x;
    private final int y;

    public static Coordinate by(int x, int y) {
        return new Coordinate(x, y);
    }

    public static Coordinate by(int position) {
        return new Coordinate(position, position);
    }

    private Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
