package name.qd.game.flappy.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Matrix4;

import java.util.LinkedList;

import name.qd.game.flappy.FlappyDemo;
import name.qd.game.flappy.spirits.Bird;
import name.qd.game.flappy.spirits.Tube;

public class PlayState extends State {
    private static final int TUBE_SPACING = 300;
    private static final int TUBE_COUNT = 4;
    private Bird bird;
    private Texture background;
    private LinkedList<Tube> tubes;
    private int score = 0;
    private int x = 0;

    public PlayState(GameStateManager gameStateManager) {
        super(gameStateManager);

        bird = new Bird(FlappyDemo.WIDTH / 4, FlappyDemo.HEIGHT / 2);
        background = new Texture("bg.png");
        tubes = new LinkedList<>();

        for(int i = 0 ; i < TUBE_COUNT; i++) {
            tubes.add(new Tube(FlappyDemo.WIDTH + (i * TUBE_SPACING + Tube.TUBE_WIDTH)));
        }
    }

    @Override
    public void handleInput() {
        if(Gdx.input.justTouched()) {
            bird.jump();
        }
    }

    @Override
    public void update(float deltaTime) {
        handleInput();
        camera.position.x = bird.getPosition().x + (FlappyDemo.WIDTH / 4);
        bird.update(deltaTime);

        Tube tube = tubes.peek();
        if(tube.collides(bird.getBound()) || bird.getPosition().y == 0) {
            gameStateManager.set(new ScoredState(gameStateManager, score));
        }

        if(camera.position.x - (FlappyDemo.WIDTH / 2)  > tube.getTopPosition().x + tube.getTopTube().getWidth()) {
            tube.reposition(tube.getTopPosition().x + ((TUBE_SPACING * (TUBE_COUNT - 1) + Tube.TUBE_WIDTH * TUBE_COUNT)));
            tubes.poll();
            tubes.add(tube);
        }
        camera.update();
    }

    @Override
    public void render(SpriteBatch spriteBatch) {
        if(x == 435) x = 0;
        spriteBatch.setProjectionMatrix(camera.combined);
        spriteBatch.begin();
        spriteBatch.draw(background, camera.position.x - (camera.viewportWidth / 2) - x++, 0);
        spriteBatch.draw(bird.getTexture(), bird.getPosition().x, bird.getPosition().y);
        for(Tube tube : tubes) {
            spriteBatch.draw(tube.getTopTube(), tube.getTopPosition().x, tube.getTopPosition().y);
            spriteBatch.draw(tube.getBottomTube(), tube.getBottomPosition().x, tube.getBottomPosition().y);
        }
        showScore(spriteBatch);
        spriteBatch.end();
    }

    private void showScore(SpriteBatch spriteBatch) {

    }

    @Override
    public void dispose() {
        bird.dispose();
        background.dispose();
        for(Tube tube : tubes) {
            tube.dispose();
        }
        camera.position.x = 0;
    }
}
