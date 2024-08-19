package com.tdl.flappybird.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.tdl.flappybird.Main;

public class MenuState extends State{
    private Texture background;
    private Texture playButton;

    public MenuState (GameStateManager gameStateManager) {
        super(gameStateManager);
        this.getCamera().setToOrtho
            (false, Main.WIDTH / 2, Main.HEIGHT / 2);
        this.background = new Texture("background.png");
        this.playButton = new Texture("playButton.png");
    }

    @Override
    protected void handleInput () {
        if (Gdx.input.justTouched()) {
            getGameStateManager().set(new PlayState());
        }
    }

    @Override
    public void update (float dt) {
        handleInput();
    }

    @Override
    public void render (SpriteBatch batch) {
        batch.setProjectionMatrix(getCamera().combined);
        batch.begin();
        batch.draw(this.background, 0, 0);
        batch.draw(this.playButton,
            getCamera().position.x - this.playButton.getWidth() / 2,
            getCamera().position.y);
        batch.end();
    }

    @Override
    public void dispose () {
        background.dispose();
        playButton.dispose();
    }
}
