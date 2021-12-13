package com.company;

abstract public class GameItem {
    // Fields
    private Point position;

    // Constructors
    public GameItem() {

    }

    public GameItem(int x, int y) {
        position = new Point(x, y);
    }

    // Methods
    abstract public void paint(AudGraphics g);

    // Getter
    public Point getPosition() {
        return position;
    }
}
