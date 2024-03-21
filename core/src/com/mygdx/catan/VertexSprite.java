package com.mygdx.catan;

import com.badlogic.gdx.graphics.g2d.PolygonRegion;
import com.badlogic.gdx.graphics.g2d.PolygonSprite;

public class VertexSprite extends PolygonSprite {
    Vertex vertex;
    public VertexSprite(PolygonRegion region, Vertex vertex) {
        super(region);
        this.vertex = vertex;
    }
}
