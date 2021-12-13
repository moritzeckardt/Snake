package com.company;

public class SnakeGame extends AudGameWindow {
    // Constants
    public static final int SQUARE_SIZE = 16;
    public static final int STEP_TIME = 100;
    public static final int GROW_AMOUNT = 2;

    // Fields
    private  int score;
    private int width;
    private int height;
    private long lastSnakeUpdate;
    private Snake snake;
    private Brick[] wall;
    private Apple apple;

    // Constructor
    public SnakeGame() {
        // Set title
        setTitle("AuD-Snake - Score: " + score);

        // Calculate width and height
        width = getGameAreaWidth() / SQUARE_SIZE;
        height = getGameAreaHeight() / SQUARE_SIZE;

        // Init last update time
        lastSnakeUpdate = System.currentTimeMillis();

        // Create snake
        snake = new Snake(5, width / 2 , height / 2);

        // Create wall
        int counter = 0;
        wall = new Brick[(width * 2) + (height * 2)];
        for (int k = 0; k < width; k++) {
            wall[counter++] = new Brick(k, 0);
            wall[counter++] = new Brick(k, height - 1);
        }
        for (int i = 0; i < height; i++) {
            wall[counter++] = new Brick(0, i);
            wall[counter++] = new Brick(width - 1, i);
        }

        // Create apple
        createNewApple();
    }

    // Private methods
    private void checkCollisions() {
        // Check snake's collision with wall and itself
        for (Brick brick : wall) {
            if (snake.collidesWith(brick) || snake.collidesWithSelf()) {
                stop();
                showDialog("You died! Score: " + score);
                //System.exit(1);
            }
        }

        // Check snake's collision with apple
        if (snake.collidesWith(apple)) {
            // Grow snake and increase score
            snake.grow(GROW_AMOUNT);
            score += apple.getValue();
            setTitle("AuD-Snake - Score: " + score);

            // Create new apple
            createNewApple();
        }
    }

    private void createNewApple() {
        // Generate random coordinates
        int random_width;
        int random_height;
        do {
            random_width = (int) ((Math.random() * ((width - 1) - 1)) + 1);
            random_height = (int) ((Math.random() * ((height - 1) - 1)) + 1);
        }
        while (snake.collidesWith(random_width, random_height));

        // Create apple
        apple = new Apple(random_width, random_height);
    }

    // Public methods
    @Override
    public void updateGame(long time) {
        // Check if step is necessary
        while (time - lastSnakeUpdate > STEP_TIME) {
            snake.step();
            checkCollisions();
            lastSnakeUpdate += STEP_TIME;
        }
    }

    @Override
    public void paintGame(AudGraphics g) {
        // Draw background
        g.setBackground(AudColor.WHITE);
        g.fillRect(0, 0, getGameAreaWidth(), getGameAreaHeight());

        // Draw snake
        snake.paint(g);

        // Draw wall
        for (Brick brick : wall) {
            brick.paint(g);
        }

        // Draw apple
        apple.paint(g);
    }

    @Override
    public void handleInput(int keyCode) {
        // Handle movement of snake
        switch (keyCode) {
            case KeyEvent.VK_RIGHT:
                snake.setNextDirection(Snake.Direction.RIGHT);
                break;
            case KeyEvent.VK_DOWN:
                snake.setNextDirection(Snake.Direction.DOWN);
                break;
            case KeyEvent.VK_LEFT:
                snake.setNextDirection(Snake.Direction.LEFT);
                break;
            case KeyEvent.VK_UP:
                snake.setNextDirection(Snake.Direction.UP);
                break;
        }
    }

    // Main
    public static void main(String[] args) {
        // Init game
        SnakeGame snakeGame = new SnakeGame();
        snakeGame.start();
    }
}
