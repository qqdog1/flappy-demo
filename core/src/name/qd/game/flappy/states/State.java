package name.qd.game.flappy.states;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

import name.qd.game.flappy.FlappyDemo;

public abstract class State {
    protected OrthographicCamera camera;
    protected Vector3 vector3;
    protected GameStateManager gameStateManager;

    protected State(GameStateManager gameStateManager) {
        this.gameStateManager = gameStateManager;
        camera = new OrthographicCamera();
        // 設定攝影機可視大小
        camera.setToOrtho(false, FlappyDemo.WIDTH, FlappyDemo.HEIGHT);
        vector3 = new Vector3();
    }

    public abstract void handleInput();
    public abstract void update(float deltaTime);
    public abstract void render(SpriteBatch spriteBatch);
    public abstract void dispose();
}
