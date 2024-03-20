package com.mygdx.catan;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.g2d.PolygonSprite;
import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch;

import java.util.ArrayList;
import java.util.HashMap;

public class MyInputProcessor implements InputProcessor {

    ArrayList<Hex> hexes;
    HashMap<String,Edge> edgeSet;
    HashMap<String,Vertex> vertexSet;
    PolygonSpriteBatch batch;

    public MyInputProcessor( ArrayList<Hex> hexes,
    HashMap<String,Edge> edgeSet,
    HashMap<String,Vertex> vertexSet, PolygonSpriteBatch batch){
        this.hexes = hexes;
        this.edgeSet = edgeSet;
        this.vertexSet = vertexSet;
        this.batch = batch;
    }

    public boolean keyDown (int keycode) {
        return false;
    }

    public boolean keyUp (int keycode) {
        return false;
    }

    public boolean keyTyped (char character) {
        return false;
    }

    public boolean touchDown (int x, int y, int pointer, int button) {
        return false;
    }

    public boolean touchUp (int x, int y, int pointer, int button) {
        return false;
    }

    public boolean touchCancelled(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    public boolean touchDragged (int x, int y, int pointer) {
        return false;
    }

    public boolean mouseMoved (int x, int y) {
        return false;
    }

    public boolean scrolled (float amountX, float amountY) {
        return false;
    }
}
