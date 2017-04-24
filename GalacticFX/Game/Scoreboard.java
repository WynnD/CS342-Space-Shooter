package Game;

import javafx.scene.canvas.GraphicsContext;

/**
 * Created by Wynn on 4/23/2017.
 */
public class Scoreboard {
    private int score;
    private Coordinate2D position;

    public Scoreboard() {
        score = 0;
        position = new Coordinate2D(5,690);
    }

    public int getScore() {
        return score;
    }

    public void addScore(int to_add) {
        score += to_add;
    }

    public void display(GraphicsContext gc) {
        gc.fillText(String.format("Score: %1$d", score), position.getX(), position.getY());
    }
}
