package com.mygdx.catan;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;

public class Board extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	ShapeRenderer shape;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
		shape = new ShapeRenderer();
	}

	@Override
	public void render () {
		ScreenUtils.clear(1, 0, 0, 1);
//		batch.begin();
//		batch.draw(img, 0, 0);
//		batch.end();
		// Begin ShapeRenderer
		shape.begin(ShapeRenderer.ShapeType.Line);

		// Draw a hexagon
		float[] hexVertices = calculateHexVertices(100, 100, 50); // Example values
		shape.polygon(hexVertices);

		// End ShapeRenderer
		shape.end();

	}

	//
	float[] calculateHexVertices(float centerX, float centerY, float size) {
		float[] vertices = new float[12]; // 6 points * 2 (X and Y for each)
		for (int i = 0; i < 6; i++) {
			vertices[i * 2] = (float) (centerX + size * Math.cos(i * Math.PI / 3));
			vertices[i * 2 + 1] = (float) (centerY + size * Math.sin(i * Math.PI / 3));
		}
		return vertices;
	}


	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}
