package com.mygdx.catan;

import com.badlogic.gdx.graphics.g2d.PolygonRegion;
import com.badlogic.gdx.graphics.g2d.PolygonSprite;

public class EdgeSprite extends PolygonSprite {
    Edge edge;
    public EdgeSprite(PolygonRegion region, Edge edge) {
        super(region);
        this.edge = edge;
    }
}
