package name.qd.game.flappy.spirits;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

public class Tube {
    public static final int TUBE_WIDTH = 75;
    private static final int FLUCTUATION = 480;
    private static final int TUBE_GAP = 150;
    private static final int LOWEST_OPENING = 240;

    private Texture topTube;
    private Texture bottomTube;
    private Vector2 topPosition;
    private Vector2 bottomPosition;
    private Rectangle boundsTop;
    private Rectangle boundsBottom;
    private Random random;

    public Tube(float x) {
        topTube = new Texture("topTube.png");
        bottomTube = new Texture("tube.png");
        random = new Random();
        topPosition = new Vector2(x, random.nextInt(FLUCTUATION) + TUBE_GAP + LOWEST_OPENING);
        bottomPosition = new Vector2(x, topPosition.y - TUBE_GAP - bottomTube.getHeight());
        boundsTop = new Rectangle(topPosition.x, topPosition.y, topTube.getWidth(), topTube.getHeight());
        boundsBottom = new Rectangle(bottomPosition.x, bottomPosition.y, bottomTube.getWidth(), bottomTube.getHeight());
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
        boundsTop.setPosition(topPosition.x, topPosition.y);
        boundsBottom.setPosition(bottomPosition.x, bottomPosition.y);
    }

    public Rectangle getTopBound() {
        return boundsTop;
    }

    public Rectangle getBottomBound() {
        return boundsBottom;
    }

    public boolean collides(Circle player) {
        return Intersector.overlaps(player, boundsTop) || Intersector.overlaps(player, boundsBottom);
    }

    public void dispose() {
        topTube.dispose();
        bottomTube.dispose();
    }
}
