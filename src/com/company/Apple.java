package com.company;

import static com.company.SnakeGame.SQUARE_SIZE;

public class Apple extends GameItem {
    // Constants
    private final int VALUE = 1;

    // Fields
    private static int nextValue;

    // Constructors
    public Apple() {

    }

    public Apple(int x, int y) {
        super(x, y);
        nextValue += VALUE;
    }

    // Methods
    @Override
    public void paint(AudGraphics g) {
        // Paint single brick
        g.setColor(AudColor.RED);
        g.fillOval(getPosition().getX() * SQUARE_SIZE, getPosition().getY() * SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE);
    }

    // Getter
    public int getValue() {
        return nextValue;
    }
}
