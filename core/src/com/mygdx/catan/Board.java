package com.mygdx.catan;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.PolygonSprite;
import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;


public class Board extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	ShapeRenderer shape;
	ArrayList<Hex> hexes;
	PolygonSprite polySprite;
	PolygonSpriteBatch polyBatch;

	//coordinate range for axial coordinate system, p,q,r.
	public static final int[] coordRange = new int[]{-2, 3};
	public static final float hexSize = 50;
	public static final float SQRT3 = (float)Math.sqrt(3);



	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
		shape = new ShapeRenderer();
		hexes = generateHexes();

		polyBatch = new PolygonSpriteBatch();

		Pixmap woodPix = new Pixmap(1,1,Pixmap.Format.RGB888 );
		Pixmap wheatPix = new Pixmap(1,1,Pixmap.Format.RGB888 );
		Pixmap rockPix = new Pixmap(1,1,Pixmap.Format.RGB888 );
		Pixmap clayPix = new Pixmap(1,1,Pixmap.Format.RGB888 );
		Pixmap sheepPix = new Pixmap(1,1,Pixmap.Format.RGB888 );

		

	}

	@Override
	public void render () {
		ScreenUtils.clear((float)79/255,(float)166/255,(float)235/255, 1);
//		batch.begin();
//		batch.draw(img, 0, 0);
//		batch.end();
		// Begin ShapeRenderer
		shape.begin(ShapeRenderer.ShapeType.Line);
		shape.setColor(1,1,1,0);
		int width = Gdx.graphics.getWidth();
		int height = Gdx.graphics.getHeight();

		for(Hex hex: hexes){
			int[] coords = hex.getCoord();
			float[] rectCoords = axialToRectCoords(coords[0], coords[1], coords[2], width/2, height/2);
			shape.polygon(calculateHexVertices(rectCoords[0], rectCoords[1], hexSize));

		}
		// Draw a hexagon
//		float[] hexVertices = calculateHexVertices(width/2, height/2, hexSize); // Example values
//		float[] hexVertices2 = calculateHexVertices(170, 50, hexSize);
//		shape.polygon(hexVertices);
//		shape.polygon(hexVertices2);

		// End ShapeRenderer
		shape.end();

	}



	//This method takes in a list of hexes which have axial coordinates
	//It then transforms them into rectangular coordinates
	private float[] axialToRectCoords(int p, int q, int r, int xCenter, int yCenter){
		float[] rectCoords = new float[]{hexSize * (SQRT3 * q + SQRT3 / 2 * r), hexSize * ((float)1.5 * r)};
		// Adjust for the center of the board
		rectCoords[0] += xCenter;
		rectCoords[1] += yCenter;
		return rectCoords;
	}

	private ArrayList<Hex>  generateHexes(){

		ArrayList<Hex> hexes = new ArrayList<>();
		for (int p = coordRange[0]; p < coordRange[1]; p++){
			for (int q = coordRange[0]; q < coordRange[1]; q++) {
				for (int r = coordRange[0]; r < coordRange[1]; r++) {
					if(p+q+r == 0){
						Hex hex = new Hex(new int[]{p, q, r});
						//Hex hex = new Hex(new int[]{0,0,0});
						hexes.add(hex);
						System.out.println("Made hex with" + hex);
						}
					}
				}
			}
		return hexes;
	}

	private float[] calculateHexVertices(float centerX, float centerY, float size) {
		float[] vertices = new float[12]; // 6 points * 2 (X and Y for each)
		for (int i = 0; i < 6; i++) {
			float angle_rad = (float) (Math.PI / 180 * (60 * i - 30));
			vertices[i * 2] = (float) (centerX + size * Math.cos(angle_rad));
			vertices[i * 2 + 1] = (float) (centerY + size * Math.sin(angle_rad));
		}
		return vertices;
	}


	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}
