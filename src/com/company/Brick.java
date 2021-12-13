package com.company;

import static com.company.SnakeGame.SQUARE_SIZE;

public class Brick extends GameItem {
    // Constructors
    public Brick() {

    }

    public Brick(int x, int y) {
        super(x, y);
    }

    // Methods
    @Override
    public void paint(AudGraphics g) {
        // Paint single brick
        g.setColor(AudColor.GREEN);
        g.fillRect(getPosition().getX() * SQUARE_SIZE, getPosition().getY() * SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE);
    }
}
