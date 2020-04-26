package name.qd.game.flappy.spirits;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;


public class Bird {
    private static final int GRAVITY = -10;
    private static final int MOVEMENT = 100;
    private static final int JUMP_HEIGHT = 300;
    private Vector3 position;
    private Vector3 velocity;
    private Texture texture;
    private Animation birdAnimation;
    private Rectangle bound;
    private Sound flapSound;

    public Bird(int x, int y) {
        position = new Vector3(x, y, 0);
        velocity = new Vector3(0, 0, 0);
        texture = new Texture("bird-anima.png");
        birdAnimation = new Animation(new TextureRegion(texture), 2, 0.5f);
        bound = new Rectangle(x , y, texture.getWidth()/2, texture.getHeight());
        flapSound = Gdx.audio.newSound(Gdx.files.internal("swish1.mp3"));
    }

    public void update(float deltaTime) {
        birdAnimation.update(deltaTime);
        if(position.y > 0) {
            velocity.add(0, GRAVITY, 0);
        }
        velocity.scl(deltaTime);
        position.add(MOVEMENT * deltaTime, velocity.y, 0);
        if(position.y < 0 ) {
            position.y = 0;
        }

        velocity.scl(1/deltaTime);
        bound.setPosition(position.x, position.y);
    }

    public void dispose() {
        texture.dispose();
        flapSound.dispose();
    }

    public void jump() {
        velocity.y = JUMP_HEIGHT;
        flapSound.play();
    }

    public Vector3 getPosition() {
        return position;
    }

    public TextureRegion getTexture() {
        return birdAnimation.getCurrentFrame();
    }

    public Rectangle getBound() {
        return bound;
    }
}
