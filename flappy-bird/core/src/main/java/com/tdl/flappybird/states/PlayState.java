package com.tdl.flappybird.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.tdl.flappybird.Main;
import com.tdl.flappybird.sprites.Bird;

public class PlayState extends State {
    private Bird bird;
    private Texture background;

    public PlayState(GameStateManager gameStateManager) {
        super(gameStateManager);
        this.bird = new Bird(50, 300);
        this.getCamera().setToOrtho
            (false, Main.WIDTH / 2, Main.HEIGHT / 2);
        this.background = new Texture("background.png");
    }
    @Override
    protected void handleInput () {
        if (Gdx.input.justTouched()) {
            bird.jump();
        }
    }

    @Override
    public void update (float dt) {
        handleInput();
        bird.update(dt);
        getCamera().position.x = bird.getPosition().x + 80;
        getCamera().update();
    }

    @Override
    public void render (SpriteBatch batch) {
        batch.setProjectionMatrix(getCamera().combined);
        batch.begin();
        batch.draw
            (background, getCamera().position.x - (getCamera().viewportWidth / 2), 0);
        batch.draw(bird.getTexture(), bird.getPosition().x, bird.getPosition().y);
        batch.end();
    }

    @Override
    public void dispose () {
        background.dispose();
        bird.dispose();
    }
}
