package com.mygdx.catan;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.PolygonRegion;
import com.badlogic.gdx.graphics.g2d.PolygonSprite;
import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch;
import com.badlogic.gdx.math.EarClippingTriangulator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class MyInputProcessor implements InputProcessor {

    ArrayList<HexSprite> hexes;
    ArrayList<EdgeSprite> edges;
    ArrayList<VertexSprite> vertices;
    PolygonSpriteBatch batch;
    Player currPlayer;
    TextureFactory t;
    EarClippingTriangulator triangulator;


    public MyInputProcessor( ArrayList<HexSprite> hexes,
    ArrayList<EdgeSprite> edges,
    ArrayList<VertexSprite> vertices, PolygonSpriteBatch batch){
        this.hexes = hexes;
        this.edges = edges;
        this.vertices = vertices;
        this.batch = batch;
        t = new TextureFactory();
        triangulator = new EarClippingTriangulator();
        this.currPlayer = null;
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

        for (int i = 0; i < edges.size(); i++){
            EdgeSprite edgeSprite = edges.get(i);
            if (edgeSprite.getBoundingRectangle().contains(x, Math.abs(Gdx.graphics.getHeight() - y))) {
                System.out.println("A right click on an edge has been clicked");
                Edge edge = edgeSprite.edge;
                edge.setPlayer(this.currPlayer);
                // Change the sprite's color

                float[] coords = edge.getPolygonCoords();
                PolygonRegion polyRegion = new PolygonRegion(t.getEdgeTexture(Color.RED), coords, triangulator.computeTriangles(coords).toArray());

                edges.set(i, new EdgeSprite(polyRegion, edge));

                return true;
            }
        }
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
