package com.tdl.flappybird.states;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public abstract class State {
    private GameStateManager gameStateManager;
    private OrthographicCamera camera;
    private Vector2 position;

    public State (GameStateManager gameStateManager){
        this.gameStateManager = gameStateManager;
        this.camera = new OrthographicCamera();
        this.position = new Vector2();
    }

    protected abstract void handleInput();
    public abstract void update (float dt);
    public abstract void render (SpriteBatch batch);
    public abstract void dispose ();

    public GameStateManager getGameStateManager () {
        return gameStateManager;
    }

    public OrthographicCamera getCamera () {
        return camera;
    }

    public Vector2 getPosition () {
        return position;
    }
}
