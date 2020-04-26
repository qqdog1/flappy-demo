package name.qd.game.flappy.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import name.qd.game.flappy.FlappyDemo;

public class MenuState extends State {
    private Texture background;
    private Texture playBtn;
    private int x = 0;

    public MenuState(GameStateManager gameStateManager) {
        super(gameStateManager);
        background = new Texture("bg.png");
        playBtn = new Texture("playbtn.png");
    }

    @Override
    public void handleInput() {
        if(Gdx.input.justTouched()) {
            gameStateManager.set(new PlayState(gameStateManager));
        }
    }

    @Override
    public void update(float deltaTime) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch spriteBatch) {
        spriteBatch.setProjectionMatrix(camera.combined);
        if(x == -435) x = 0;
        spriteBatch.begin();
        spriteBatch.draw(background, x--, 0);
        spriteBatch.draw(playBtn, (FlappyDemo.WIDTH/2) - (playBtn.getWidth()/2), (FlappyDemo.HEIGHT/2) - (playBtn.getHeight()/2));
        spriteBatch.end();
    }

    @Override
    public void dispose() {
        background.dispose();;
        playBtn.dispose();
    }
}
