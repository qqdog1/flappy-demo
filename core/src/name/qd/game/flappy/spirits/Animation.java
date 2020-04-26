package name.qd.game.flappy.spirits;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

public class Animation {
    private Array<TextureRegion> frames;
    private float maxFrameTime;
    private float currentFrameTime;
    private int frameCount;
    private int currentFrame;

    public Animation(TextureRegion textureRegion, int frameCount, float cycleTime) {
        frames = new Array<>();
        int frameWidth = textureRegion.getRegionWidth() / frameCount;
        for(int i = 0 ; i <= frameCount ; i++) {
            frames.add(new TextureRegion(textureRegion, i * frameWidth, 0, frameWidth, textureRegion.getRegionHeight()));
        }
        this.frameCount = frameCount;
        maxFrameTime = cycleTime / frameCount;
        currentFrame = 0;
    }

    public void update(float delteTime) {
        currentFrameTime += delteTime;
        if(currentFrameTime >= maxFrameTime) {
            currentFrame++;
            currentFrameTime = 0;
        }
        if(currentFrame >= frameCount) {
            currentFrame = 0;
        }
    }

    public TextureRegion getCurrentFrame() {
        return frames.get(currentFrame);
    }
}
