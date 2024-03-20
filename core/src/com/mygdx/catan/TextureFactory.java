package com.mygdx.catan;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class TextureFactory {
    public TextureRegion getEdgeTexture() {
        Pixmap pix = new Pixmap(1,1,Pixmap.Format.RGB888);
        pix.setColor(Color.BLACK);
        pix.fill();
        return new TextureRegion(new Texture(pix));
    }

}

