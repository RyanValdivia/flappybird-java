package com.tdl.flappybird.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.tdl.flappybird.Main;
import com.tdl.flappybird.game.GameStateManager;
import com.tdl.flappybird.sprites.Bird;
import com.tdl.flappybird.sprites.Tube;

public class PlayState extends State {
    private static final int TUBE_SPACING = 125;
    private static final int TUBE_COUNT = 4;

    private Array<Tube> tubes;
    private Bird bird;
    private Texture background;

    public PlayState(GameStateManager gameStateManager) {
        super(gameStateManager);
        this.bird = new Bird(50, 300);
        this.getCamera().setToOrtho
            (false, Main.WIDTH / 2, Main.HEIGHT / 2);
        this.background = new Texture("background.png");
        this.tubes = new Array<>();

        for (int i = 1; i <= TUBE_COUNT; i++) {
            tubes.add(new Tube(i * (TUBE_SPACING + Tube.TUBE_WIDTH)));
        }
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

        for (int i = 0; i < this.tubes.size; i++) {
            Tube tube = this.tubes.get(i);
            if (getCamera().position.x - (getCamera().viewportWidth / 2) >
                tube.getPosTopTube().x + tube.getTopTube().getWidth()) {
                tube.reposition(tube.getPosTopTube().x +
                    (Tube.TUBE_WIDTH + TUBE_SPACING) * TUBE_COUNT);
            }

            if (tube.collides(bird.getBounds())) {
                getGameStateManager().set(new MenuState(getGameStateManager()));
            }
        }

        getCamera().update();
    }

    @Override
    public void render (SpriteBatch batch) {
        batch.setProjectionMatrix(getCamera().combined);
        batch.begin();
        batch.draw
            (background, getCamera().position.x - (getCamera().viewportWidth / 2), 0);
        batch.draw(bird.getTexture(), bird.getPosition().x, bird.getPosition().y);

        for (Tube tube : tubes) {
            batch.draw(tube.getTopTube(), tube.getPosTopTube().x, tube.getPosTopTube().y);
            batch.draw(tube.getBottomTube(), tube.getPosBottomTube().x, tube.getPosBottomTube().y);
        }

        batch.end();
    }

    @Override
    public void dispose () {
        background.dispose();
        bird.dispose();
        for (Tube tube: this.tubes) {
            tube.dispose();
        }
    }
}
