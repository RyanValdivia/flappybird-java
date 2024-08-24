package com.tdl.flappybird.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

public class Tube {
    public static final int TUBE_WIDTH = 52;

    private static final int FLUCTUATION = 130;
    private static final int TUBE_GAP = 100;
    private static final int LOWEST_LIMIT = 120;

    private Texture topTube;
    private Texture bottomTube;

    private Vector2 posTopTube;
    private Vector2 posBottomTube;

    private Rectangle boundsTop;
    private Rectangle boundsBottom;

    private Random random;


    public Tube (float x) {
        this.topTube = new Texture("toptube.png");
        this.bottomTube = new Texture("bottomtube.png");
        this.random = new Random();

        this.posTopTube = new Vector2(x,
            random.nextFloat(FLUCTUATION) + TUBE_GAP + LOWEST_LIMIT);
        this.posBottomTube = new Vector2(x,
            posTopTube.y - TUBE_GAP - bottomTube.getHeight());

        this.boundsTop = new Rectangle(
            this.posTopTube.x, this.posTopTube.y,
            this.topTube.getWidth(), this.topTube.getHeight()
        );

        this.boundsBottom = new Rectangle(
            this.posBottomTube.x, this.posBottomTube.y,
            this.bottomTube.getWidth(), this.bottomTube.getHeight()
        );
    }

    public Texture getTopTube () {
        return this.topTube;
    }

    public Texture getBottomTube () {
        return this.bottomTube;
    }

    public Vector2 getPosTopTube () {
        return this.posTopTube;
    }

    public Vector2 getPosBottomTube () {
        return this.posBottomTube;
    }

    public void reposition (float x) {
        this.posTopTube.x = x;
        this.posBottomTube.x = x;

        this.boundsTop.x = x;
        this.boundsBottom.x = x;
    }

    public boolean collides (Rectangle bird) {
        return bird.overlaps(this.boundsTop) || bird.overlaps(this.boundsBottom);
    }

    public void dispose () {
        this.topTube.dispose();
        this.bottomTube.dispose();
    }
}
