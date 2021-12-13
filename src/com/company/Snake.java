package com.company;

import static com.company.SnakeGame.SQUARE_SIZE;

public class Snake {
    // Fields
    private AudColor color = AudColor.BLUE;
    private Direction nextDirection = Direction.RIGHT;
    private Direction lastDirection;
    private Point[] points;

    // Enums
    public enum Direction {
        RIGHT, DOWN, LEFT, UP
    }

    // Constructors
    public Snake() {

    }

    public Snake(int length, int x, int y) {
        // Check length
        if (length < 1) {
            throw new IllegalArgumentException("Invalid length.");
        }

        // Initialize snake's position via array
        points = new Point[length];
        points[0] = new Point(x, y);
    }

    public Snake(int x, int y) {
        this(5, x, y);
    }

    // Methods
    public void paint(AudGraphics g) {
        // Set color and draw snake
        g.setColor(color);
        for (Point point : points) {
            if (point != null)
                g.fillRect(point.getX() * SQUARE_SIZE, point.getY() * SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE);
        }
    }

    public void step() {
        // Copy snake
        System.arraycopy(points, 0, points, 1, points.length - 1);

        // Set first point of snake
        switch (nextDirection) {
            case RIGHT:
                points[0] = new Point(points[0].getX() + 1, points[0].getY());
                break;
            case DOWN:
                points[0] = new Point(points[0].getX() , points[0].getY() + 1);
                break;
            case LEFT:
                points[0] = new Point(points[0].getX() - 1, points[0].getY());
                break;
            case UP:
                points[0] = new Point(points[0].getX() , points[0].getY() - 1);
                break;
        }

        // Set last direction
        lastDirection = nextDirection;
    }

    public boolean collidesWith(GameItem item) {
        // Call method
        return collidesWith(item.getPosition().getX(), item.getPosition().getY());
    }

    public boolean collidesWith(int x, int y) {
        // Check collision with game item
        return points[0].getX() == x && points[0].getY() == y;
    }

    public boolean collidesWithSelf() {
        // Check collision with itself
        for (int i = 1; i < points.length; i++) {
            if (points[i] != null) {
                if (points[0].getX() == points[i].getX() && points[0].getY() == points[i].getY()) {
                    return true;
                }
            }
        }
        return false;
    }

    public void grow(int amount) {
        // Check amount
        if (amount < 1) {
            throw new IllegalArgumentException("Invalid amount");
        }

        // Create new snake
        Point[] tempSnake = new Point[points.length + amount];
        System.arraycopy(points, 0, tempSnake, 0, points.length);
        points = tempSnake;
    }

    // Getter & Setter
    public Direction getNextDirection() {
        return nextDirection;
    }

    public void setNextDirection(Direction nextDirection) {
        // Check last direction
        if ((lastDirection.ordinal() + 2) % Direction.values().length == nextDirection.ordinal()) {
            return;
        }

        this.nextDirection = nextDirection;
    }
}
