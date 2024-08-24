package com.tdl.flappybird.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

public class Animation {
    private Array<TextureRegion> frames;
    private float maxFrameTime;
    private float currentFrameTime;
    private int frameCount;
    private int currentFrame;

    public Animation (Texture region, int frameCount, float cycleTime) {
        this.frames = new Array<>();
        int frameWidth = region.getWidth() / frameCount;
        for (int i = 0; i < frameCount; i++) {
            frames.add(new TextureRegion
                (region, i * frameWidth, 0, frameWidth, region.getHeight()));
        }
        this.frameCount = frameCount;
        this.currentFrameTime = 0;
        this.maxFrameTime = cycleTime / frameCount;
        this.currentFrame = 0;
    }

    public void update (float dt) {
        currentFrameTime += dt;
        if (currentFrameTime > maxFrameTime) {
            currentFrame++;
            currentFrameTime = 0;
        }
        if (currentFrame >= frameCount) {
            currentFrame = 0;
        }
    }

    public TextureRegion getCurrentFrame() {
        return frames.get(currentFrame);
    }
}
