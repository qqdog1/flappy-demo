package name.qd.game.flappy.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;

import name.qd.game.flappy.FlappyDemo;
import name.qd.game.flappy.spirits.Bird;
import name.qd.game.flappy.spirits.Tube;

public class PlayState extends State {
    private static final int TUBE_SPACING = 200;
    private static final int TUBE_COUNT = 4;
    private Bird bird;
    private Texture background;
    private Array<Tube> tubes;

    public PlayState(GameStateManager gameStateManager) {
        super(gameStateManager);
//        camera.setToOrtho(false, FlappyDemo.WIDTH / 2, FlappyDemo.HEIGHT / 2);
        camera.setToOrtho(false, FlappyDemo.WIDTH, FlappyDemo.HEIGHT);
        bird = new Bird(FlappyDemo.WIDTH / 4, FlappyDemo.HEIGHT / 2);
        background = new Texture("bg.png");
        tubes = new Array<>();

        for(int i = 0 ; i < TUBE_COUNT; i++) {
            tubes.add(new Tube(i * TUBE_SPACING + Tube.TUBE_WIDGH));
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
        bird.update(deltaTime);
        camera.position.x = bird.getPosition().x - 100;



        for(Tube tube : tubes) {
            if(camera.position.x  > tube.getTopPosition().x + tube.getTopTube().getWidth()) {
                tube.reposition(tube.getTopPosition().x + ((Tube.TUBE_WIDGH + TUBE_SPACING) * TUBE_COUNT));
            }
        }
        camera.update();
    }

    @Override
    public void render(SpriteBatch spriteBatch) {
        spriteBatch.setProjectionMatrix(camera.combined);
        spriteBatch.begin();
        spriteBatch.draw(background, camera.position.x - (camera.viewportWidth / 2), 0);
        spriteBatch.draw(bird.getTexture(), bird.getPosition().x, bird.getPosition().y);
        for(Tube tube : tubes) {
            spriteBatch.draw(tube.getTopTube(), tube.getTopPosition().x, tube.getTopPosition().y);
            spriteBatch.draw(tube.getBottomTube(), tube.getBottomPosition().x, tube.getBottomPosition().y);
        }
        spriteBatch.end();
    }

    @Override
    public void dispose() {

    }
}
