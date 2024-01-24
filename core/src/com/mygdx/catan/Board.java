package com.mygdx.catan;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;

import java.util.ArrayList;
import java.util.Arrays;


public class Board extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	ShapeRenderer shape;
	ArrayList<Hex[]> hexes;
	//coordinate range for axial coordinate system, p,q,r.
	public static final int[] coordRange = new int[]{-2, 3};
	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
		shape = new ShapeRenderer();
	}

	@Override
	public void render () {
		ScreenUtils.clear((float)79/255,(float)166/255,(float)235/255, 1);
//		batch.begin();
//		batch.draw(img, 0, 0);
//		batch.end();
		// Begin ShapeRenderer
		shape.begin(ShapeRenderer.ShapeType.Line);

		// Draw a hexagon
		float[] hexVertices = calculateHexVertices(100, 100, 50); // Example values
		float[] hexVertices2 = calculateHexVertices(170, 50, 50);
		shape.polygon(hexVertices);
		shape.polygon(hexVertices2);

		// End ShapeRenderer
		shape.end();

	}

	private Hex[] generateHexes(){
		ArrayList<Hex> hexes = new ArrayList<>();
		for (int q = coordRange[0]; q < coordRange[1]; q++){
			for (int p = coordRange[0]; q < coordRange[1]; p++) {
				for (int r = coordRange[0]; q < coordRange[1]; r++) {

					}
				}
			}
		}
	}

	private float[] calculateHexVertices(float centerX, float centerY, float size) {
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
