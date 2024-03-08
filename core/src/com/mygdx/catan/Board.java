package com.mygdx.catan;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.EarClippingTriangulator;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.ShortArray;

import java.util.*;


public class Board extends ApplicationAdapter {
	PolygonSpriteBatch batch;
	Texture img;
	ShapeRenderer shape;
	ArrayList<Hex> hexes;
	ArrayList<PolygonSprite> sprites;
	HashMap<String,Edge> edgeSet;
	HashMap<String,Vertex> vertexSet;

	//coordinate range for axial coordinate system, ,q,r.
	public static final int[] coordRange = new int[]{-2, 3};
	public static final float hexSize = 50;
	public static final float SQRT3 = (float)Math.sqrt(3);



	@Override
	public void create () {
		batch = new PolygonSpriteBatch();
		img = new Texture("badlogic.jpg");
		shape = new ShapeRenderer();
		hexes = generateHexes();
		sprites = new ArrayList<PolygonSprite>();
		edgeSet = new HashMap<>();
		vertexSet = new HashMap<>();
		EarClippingTriangulator triangulator = new EarClippingTriangulator();


		int width = Gdx.graphics.getWidth();
		int height = Gdx.graphics.getHeight();
		TextureRegion sampleRegion = createSampleTextureRegion();

		for(Hex hex: hexes){
			int[] coords = hex.getCoord();
			float[] rectCoords = axialToRectCoords(coords[0], coords[1], coords[2], width/2, height/2);
			float[] vertices = calculateHexVertices(rectCoords[0], rectCoords[1], hexSize);
			float[][] edges = calculateHexEdges(vertices);
			PolygonRegion polyRegion = new PolygonRegion(hex.getTextureRegion(), calculateHexVertices(rectCoords[0], rectCoords[1], hexSize), triangulator.computeTriangles(vertices).toArray());
			sprites.add(new PolygonSprite(polyRegion));

			for(int i = 0; i < vertices.length; i+=2){
				float x = vertices[i];
				float y = vertices[i+1];
				String formattedVertexKey = vertexToString(x, y);

				if(!vertexSet.containsKey(formattedVertexKey)){
					vertexSet.put(formattedVertexKey, new Vertex(x,y));
				}

				Vertex vertex = vertexSet.get(formattedVertexKey);
				hex.addVertex(vertex);
				vertex.addHexes(hex);

			}


			for(int i = 0; i < edges.length; i+=1){
				float x1 = edges[i][0];
				float y1 = edges[i][1];
				float x2 = edges[i][2];
				float y2 = edges[i][3];
				String formattedEdgeKey = edgeToString(x1, y1, x2, y2);

				if(!edgeSet.containsKey(formattedEdgeKey)){
					edgeSet.put(formattedEdgeKey, new Edge(x1, y1, x2, y2));
				}

				Edge edge = edgeSet.get(formattedEdgeKey);
				hex.addEdge(edge);
				edge.addHexes(hex);


				//because we added the vertices already, im going to add the relationship between the two here
				Vertex v1 = vertexSet.get(vertexToString(x1, y1));
				Vertex v2 = vertexSet.get(vertexToString(x2, y2));

				edge.addVertex(v1);
				edge.addVertex(v2);

				v1.addEdges(edge);
				v2.addEdges(edge);

			}


		}


	}

	@Override
	public void render () {
		ScreenUtils.clear((float)79/255,(float)166/255,(float)235/255, 1);

		batch.begin();
		for (int i = 0; i < sprites.size(); i++){

			PolygonSprite sprite = sprites.get(i);
			sprite.draw(batch);
		}

//		batch.draw(img, 0, 0);
		batch.end();

		
		// Begin ShapeRenderer
//		shape.begin(ShapeRenderer.ShapeType.Line);
//		shape.setColor(1,1,1,0);
//		int width = Gdx.graphics.getWidth();
//		int height = Gdx.graphics.getHeight();
//
////
//		for(Hex hex: hexes){
//			int[] coords = hex.getCoord();
//			float[] rectCoords = axialToRectCoords(coords[0], coords[1], coords[2], width/2, height/2);
//			shape.polygon(calculateHexVertices(rectCoords[0], rectCoords[1], hexSize));
//
//		}
		// Draw a hexagon
//		float[] hexVertices = calculateHexVertices(width/2, height/2, hexSize); // Example values
//		float[] hexVertices2 = calculateHexVertices(170, 50, hexSize);
//		shape.polygon(hexVertices);
//		shape.polygon(hexVertices2);

		// End ShapeRenderer
		shape.end();

	}


	private TextureRegion createSampleTextureRegion() {
		// Create a Pixmap of 100x100 pixels with RGBA8888 format for high quality.
		Pixmap pixmap = new Pixmap(100, 100, Pixmap.Format.RGBA8888);

		// Fill the pixmap with red color.
		pixmap.setColor(1, 0, 0, 1); // RGBA values for red.
		pixmap.fill(); // Fill the entire pixmap with the current color.

		// Create a texture from the Pixmap.
		Texture texture = new Texture(pixmap);

		// Important: Dispose of the Pixmap to free up memory resources.
		pixmap.dispose();

		// Return a new TextureRegion based on the Texture.
		return new TextureRegion(texture);
	}
	private Texture createSampleTexture() {
		// Create a Pixmap of 100x100 pixels with RGBA8888 format for high quality.
		Pixmap pixmap = new Pixmap(100, 100, Pixmap.Format.RGBA8888);

		// Fill the pixmap with red color.
		pixmap.setColor(1, 0, 0, 1); // RGBA values for red.
		pixmap.fill(); // Fill the entire pixmap with the current color.

		// Create a texture from the Pixmap.
		Texture texture = new Texture(pixmap);

		// Important: Dispose of the Pixmap to free up memory resources.
		pixmap.dispose();

		// Return a new TextureRegion based on the Texture.
		return texture;
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

	private float[][] calculateHexEdges(float[] vertices) {
		float[][] edges = new float[6][4]; // 6 edges, each defined by 4 numbers (x1, y1, x2, y2)

		for (int i = 0; i < 6; i++) {
			int next = (i + 1) % 6; // Wrap around to connect the last vertex to the first

			// Starting point of the edge
			float x1 = vertices[i * 2];
			float y1 = vertices[i * 2 + 1];

			// Ending point of the edge
			float x2 = vertices[next * 2];
			float y2 = vertices[next * 2 + 1];

			// Populate the edge array
			edges[i][0] = x1;
			edges[i][1] = y1;
			edges[i][2] = x2;
			edges[i][3] = y2;
		}

		return edges;
	}

	public static String vertexToString(float x, float y) {
		return String.format("%.2f,%.2f", x, y);
	}


	public static String edgeToString(float x1, float y1, float x2, float y2) {
		// If direction is not important, sort the points
		// For simplicity, here we assume direction matters
		return String.format("%.2f,%.2f to %.2f,%.2f", x1, y1, x2, y2);
	}


	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}
