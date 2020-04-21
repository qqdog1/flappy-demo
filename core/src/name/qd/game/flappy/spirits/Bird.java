package name.qd.game.flappy.spirits;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector3;

public class Bird {
    private static final int GRAVITY = -10;
    private static final int MOVEMENT = 100;
    private Vector3 position;
    private Vector3 velocity;
    private Texture bird;
    private Circle bound;

    public Bird(int x, int y) {
        position = new Vector3(x, y, 0);
        velocity = new Vector3(0, 0, 0);
        bird = new Texture("bird.png");
        bound = new Circle(x + (bird.getWidth()/2), y + (bird.getHeight()/2), bird.getHeight()/2);
    }

    public void update(float deltaTime) {
        if(position.y > 0) {
            velocity.add(0, GRAVITY, 0);
        }
        velocity.scl(deltaTime);
        position.add(MOVEMENT * deltaTime, velocity.y, 0);
        if(position.y < 0 ) {
            position.y = 0;
        }

        velocity.scl(1/deltaTime);
        bound.setPosition(position.x + (bird.getWidth()/2), position.y + (bird.getHeight()/2));
    }

    public void jump() {
        velocity.y = 300;
    }

    public Vector3 getPosition() {
        return position;
    }

    public Texture getTexture() {
        return bird;
    }

    public Circle getBound() {
        return bound;
    }
}
