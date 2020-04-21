package name.qd.game.flappy.spirits;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

public class Tube {
    public static final int TUBE_WIDGH = 75;
    private static final int FLUCTUATION = 500;
    private static final int TUBE_GAP = 120;
    private static final int LOWEST_OPENING = 240;

    private Texture topTube;
    private Texture bottomTube;
    private Vector2 topPosition;
    private Vector2 bottomPosition;
    private Random random;

    public Tube(float x) {
        topTube = new Texture("topTube.png");
        bottomTube = new Texture("tube.png");
        random = new Random();
        topPosition = new Vector2(x, random.nextInt(FLUCTUATION) + TUBE_GAP + LOWEST_OPENING);
        bottomPosition = new Vector2(x, topPosition.y - TUBE_GAP - bottomTube.getHeight());
    }

    public Texture getTopTube() {
        return topTube;
    }

    public Texture getBottomTube() {
        return bottomTube;
    }

    public Vector2 getTopPosition() {
        return topPosition;
    }

    public Vector2 getBottomPosition() {
        return bottomPosition;
    }

    public void reposition(float x) {
        topPosition.set(x, random.nextInt(FLUCTUATION) + TUBE_GAP + LOWEST_OPENING);
        bottomPosition.set(x, topPosition.y - TUBE_GAP - bottomTube.getHeight());
    }
}
