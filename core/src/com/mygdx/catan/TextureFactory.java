package com.mygdx.catan;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class TextureFactory {
    public TextureRegion getEdgeTexture() {
        Pixmap pix = new Pixmap(1,1,Pixmap.Format.RGB888);

        pix.setColor(Color.rgb888(244,164,96));
        pix.fill();
        return new TextureRegion(new Texture(pix));
    }

    public TextureRegion getEdgeTexture(Color color) {
        Pixmap pix = new Pixmap(1,1,Pixmap.Format.RGB888);
        pix.setColor(color);
        pix.fill();
        return new TextureRegion(new Texture(pix));
    }

    public TextureRegion getVertexTexture() {
        Pixmap pix = new Pixmap(1,1,Pixmap.Format.RGB888);
        pix.setColor(Color.GOLDENROD);
        pix.fill();
        return new TextureRegion(new Texture(pix));
    }

    public TextureRegion getVertexTexture(Color color) {
        Pixmap pix = new Pixmap(1,1,Pixmap.Format.RGB888);
        pix.setColor(color);
        pix.fill();
        return new TextureRegion(new Texture(pix));
    }

}

