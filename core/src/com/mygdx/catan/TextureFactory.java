package com.mygdx.catan;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class TextureFactory {
    public TextureRegion getEdgeTexture() {
        Pixmap pix = new Pixmap(1,1,Pixmap.Format.RGB888);
        pix.fill();
        return new TextureRegion(new Texture(pix));
    }

    public TextureRegion getEdgeTexture(Color color) {
        Pixmap pix = new Pixmap(1,1,Pixmap.Format.RGB888);
        pix.setColor(color.r, color.g, color.b, 1);
        pix.fill();
        return new TextureRegion(new Texture(pix));
    }

}

