package com.mygdx.game.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.world.GameMap;

public class Player extends Entity {

    private static final int SPEED = 80;
    private static final int JUMP_VELOCITY = 5;

    Texture image;


    public void create (EntitySnapshot snapshot, EntityType type, GameMap map) {
        super.create(snapshot, type, map);
        image = new Texture("player.png");
        //spawnRadius = snapshot.getFloat("SpawnRadius", 50);
    }


    @Override
    public void update(float deltaTime, float gravity){
        if(Gdx.input.isKeyPressed(Input.Keys.SPACE) && grounded)
            this.velocityY += JUMP_VELOCITY * getWeight();
        else if(Gdx.input.isKeyPressed(Input.Keys.SPACE) && !grounded && this.velocityY > 0)
            this.velocityY += JUMP_VELOCITY * getWeight() * deltaTime;


        super.update(deltaTime, gravity); //apply gravity

        if(Gdx.input.isKeyPressed(Input.Keys.LEFT))
            moveX(-SPEED * deltaTime);

        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT))
            moveX(SPEED * deltaTime);

    }


    @Override
    public EntitySnapshot getSaveSnapshot () {
        EntitySnapshot snapshot = super.getSaveSnapshot();
        //snapShot.putFloat("spawnRadius", spawnRadius);
        return snapshot;
    }



    @Override
    public void render(SpriteBatch batch) {
        batch.draw(image, pos.x, pos.y, getWidth(), getHeight());
    }
}
