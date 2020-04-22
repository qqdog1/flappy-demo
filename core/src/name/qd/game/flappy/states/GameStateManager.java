package name.qd.game.flappy.states;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Stack;

public class GameStateManager {
    private Stack<State> status = new Stack<>();

    public GameStateManager() {
    }

    public void push(State state) {
        status.push(state);
    }

    public void pop() {
        status.pop().dispose();
    }

    public void set(State state) {
        status.pop().dispose();
        status.push(state);
    }

    public void update(float deltaTime) {
        status.peek().update(deltaTime);
    }

    public void render(SpriteBatch spriteBatch) {
        status.peek().render(spriteBatch);
    }
}
