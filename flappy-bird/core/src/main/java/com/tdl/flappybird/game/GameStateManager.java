package com.tdl.flappybird.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.tdl.flappybird.states.State;

import java.util.Stack;

public class GameStateManager {
    private Stack<State> states;

    public GameStateManager(){
        this.states = new Stack<>();
    }

    public void push(State state){
        this.states.push(state);
    }

    public void pop(){
        this.states.pop().dispose();
    }

    public void set(State state){
        this.pop();
        this.states.push(state);
    }

    public void update(float dt){
        this.states.peek().update(dt);
    }

    public void render(SpriteBatch batch){
        this.states.peek().render(batch);
    }
}
