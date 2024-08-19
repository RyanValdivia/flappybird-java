package com.tdl.flappybird.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class Bird {
    private static final int GRAVITY = -15;
    private static final int MOVEMENT = 100;

    private Vector2 position;
    private Vector2 velocity;

    private Texture texture;

    public Bird (int x, int y) {
        position = new Vector2(x, y);
        velocity = new Vector2(0, 0);
        texture = new Texture("bird.png");
    }

    public void update (float dt) {
        if (position.y > 0) {
            velocity.add(0, GRAVITY);
        }
        velocity.scl(dt);
        position.add(MOVEMENT * dt, velocity.y);
        if (position.y < 0) {
            position.y = 0;
        }
        velocity.scl(1  / dt);
    }

    public void dispose () {
        texture.dispose();
    }

    public Vector2 getPosition () {
        return this.position;
    }
}
