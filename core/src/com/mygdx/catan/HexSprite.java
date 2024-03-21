package com.mygdx.catan;

import com.badlogic.gdx.graphics.g2d.PolygonRegion;
import com.badlogic.gdx.graphics.g2d.PolygonSprite;

public class HexSprite extends PolygonSprite {
    Hex hex;
    public HexSprite(PolygonRegion region, Hex hex) {
        super(region);
        this.hex = hex;
    }
}
