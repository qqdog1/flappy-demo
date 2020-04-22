package name.qd.game.flappy.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;

public class ScoredState extends State {
    private int score;
    private Array<Texture> scores;
    private Texture background;
    private int x = 0;

    public ScoredState(GameStateManager gameStateManager, int score) {
        super(gameStateManager);
        this.score = score;
        scores = new Array<>();
        background = new Texture("bg.png");
    }

    @Override
    public void handleInput() {
        if(Gdx.input.justTouched()) {
            gameStateManager.set(new MenuState(gameStateManager));
        }
    }

    @Override
    public void update(float deltaTime) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch spriteBatch) {
        if(x == -435) x = 0;
        spriteBatch.begin();
        spriteBatch.draw(background, x--, 0);
        spriteBatch.end();
    }

    @Override
    public void dispose() {
        background.dispose();
        for(Texture number : scores) {
            number.dispose();
        }
    }
}
