package com.tdl.flappybird.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Bird {
    private static final int GRAVITY = -10;
    private static final int MOVEMENT = 100;

    private Vector2 position;
    private Vector2 velocity;

    private Rectangle bounds;

    private Texture texture;

    public Bird (int x, int y) {
        this.position = new Vector2(x, y);
        this.velocity = new Vector2(0, 0);
        this.texture = new Texture("bird.png");

        this.bounds = new Rectangle(x, y, texture.getWidth(), texture.getHeight());
    }

    public void update (float dt) {
        if (this.position.y > 0) {
            this.velocity.add(0, GRAVITY);
        }
        this.velocity.scl(dt);
        this.position.add(MOVEMENT * dt, velocity.y);
        if (this.position.y < 0) {
            this.position.y = 0;
        }
        this.velocity.scl(1  / dt);
        this.bounds.x = this.position.x;
        this.bounds.y = this.position.y;
    }

    public void dispose () {
        this.texture.dispose();
    }

    public void jump () {
        this.velocity.y = 300;
    }

    public Vector2 getPosition () {
        return this.position;
    }

    public Texture getTexture () {
        return this.texture;
    }

    public Rectangle getBounds () {
        return this.bounds;
    }
}
