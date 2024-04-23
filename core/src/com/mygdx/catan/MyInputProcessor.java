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
    TextureFactory textureFactory;
    EarClippingTriangulator triangulator;


    public MyInputProcessor( ArrayList<HexSprite> hexes,
    ArrayList<EdgeSprite> edges,
    ArrayList<VertexSprite> vertices, PolygonSpriteBatch batch){
        this.hexes = hexes;
        this.edges = edges;
        this.vertices = vertices;
        this.batch = batch;
        textureFactory = new TextureFactory();
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


        for (int i = 0; i < vertices.size(); i++){
            VertexSprite vertexSprite = vertices.get(i);
            if (vertexSprite.getBoundingRectangle().contains(x, Math.abs(Gdx.graphics.getHeight() - y))) {

                VertexSprite newVertex;
                Vertex vertex = vertexSprite.vertex;
                if(vertex.getPlayer() != null || vertex.hasCity() ||(!currPlayer.checkSettlementPossible() && !currPlayer.checkCityPossible())){
                    continue;
                }
                vertex.setPlayer(this.currPlayer);
                float[] coords = vertex.getPolygonCoords();

                this.currPlayer.colonizeVertex(vertex);
                // Change the sprite's color
                System.out.println("A right click on an edge has been clicked for player: " + this.currPlayer.playerId);

                System.out.println(vertexSprite.vertex);
                PolygonRegion polyRegion = new PolygonRegion(textureFactory.getEdgeTexture(vertex.getPlayer().getColor()), coords, triangulator.computeTriangles(coords).toArray());
                newVertex = new VertexSprite(polyRegion, vertex);
                vertices.set(i, newVertex);
                return true;


            }

        }



        for (int i = 0; i < edges.size(); i++){
           EdgeSprite edgeSprite = edges.get(i);
            if (edgeSprite.getBoundingRectangle().contains(x, Math.abs(Gdx.graphics.getHeight() - y))) {
                System.out.println("A right click on an edge has been clicked");
                EdgeSprite newEdge;
                Edge edge = edgeSprite.edge;
                if(edge.getPlayer() != null){
                    continue;
                }
                edge.setPlayer(this.currPlayer);
                this.currPlayer.colonizeEdge(edge);
                // Change the sprite's color

                System.out.println("A right click on an edge has been clicked for player: " + this.currPlayer.playerId);

                float[] coords = edge.getPolygonCoords();
                System.out.println(edgeSprite.edge);
                PolygonRegion polyRegion = new PolygonRegion(textureFactory.getEdgeTexture(edge.getPlayer().getColor()), coords, triangulator.computeTriangles(coords).toArray());
                newEdge = new EdgeSprite(polyRegion, edge);
                edges.set(i, newEdge);


            }

        }


        return true;
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
