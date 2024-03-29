package com.mygdx.catan;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.PolygonSprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;

public class Hex {
    public static final Resource[] resources = new Resource[]{Resource.WOOD, Resource.SHEEP, Resource.CLAY, Resource.ROCK, Resource.WHEAT};

    private Resource resourceType;
    private int value;
    private int[] axialCoord;
    private float[] rectCoords;
    private HashSet<Vertex> vertices;
    private HashSet<Edge> edges;
    private com.badlogic.gdx.graphics.g2d.TextureRegion textureRegion ;

    public enum Resource{
        WOOD("WOOD"), WHEAT("WHEAT"), SHEEP("SHEEP"), ROCK("ROCK"), CLAY("CLAY");
        private String value;
        private Resource(String value){
            this.value = value;
        }

        public String getString(){
            return value;
        }

        public boolean equals(Resource r){
            return value.equals(r.getString());
        }

    }

    public Hex(Resource type, int value, int[] axialCoord) {
        this.resourceType = type;
        this.axialCoord = axialCoord;
        this.value = value;
        this.rectCoords = null;
        this.vertices = new HashSet<>();
        this.edges = new HashSet<>();

        this.textureRegion = getTextureRegion(this.resourceType);
    }

    public Hex(int[] axialCoord){
        this.axialCoord = axialCoord;
        this.resourceType = getRandResource();
        this.value = getRandValue();

        this.vertices = new HashSet<>();
        this.edges = new HashSet<>();

        this.textureRegion = getTextureRegion(this.resourceType);
    }

    //TODO Need to move this outside of hex initialization, make it similar to edge sprite generation.
    private TextureRegion getTextureRegion(Resource resourceType) {
        Pixmap pix = new Pixmap(1,1,Pixmap.Format.RGB888 );
        switch (resourceType){
            case WOOD:
                pix.setColor(0.039f, 0.29f, 0.12f, 0.5F); // Light green
                break;
            case WHEAT:
                pix.setColor(0.93f, 0.9f, 0.55f, 0.5F); // A light, golden brown
                break;
            case ROCK:
                pix.setColor(0.5f, 0.5f, 0.5f, 0.5F); // Medium gray
                break;
            case CLAY:
                pix.setColor(0.72f, 0.45f, 0.2f, 0.5F); // A reddish-brown
                break;
            case SHEEP:
                pix.setColor(0.6f, 0.8f, 0.4f, 0.5F); // Light green
                break;
            default:
                pix.setColor(1, 1, 1, 0.5F); // Default to white if none match
                break;
        }
        pix.fill();

        TextureRegion region = new TextureRegion(new Texture(pix));
        pix.dispose(); // Don't forget to dispose of the Pixmap to free memory!
        return region;
    }

    public static int getRandValue(){
        Random random = new Random();
        // Generate a random number between 2 and 12
        int randomNumber = 2 + random.nextInt(11);
        return randomNumber;
    }

    public static Resource getRandResource(){
        Random random = new Random();
        int randomResourceIndex = random.nextInt(5);
        return resources[randomResourceIndex];
    }

    public Resource getType() {
        return resourceType;
    }

    public void setType(Resource type) {
        this.resourceType = type;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public HashSet<Vertex> getVertices() {
        return vertices;
    }

    public void setVertices(HashSet<Vertex> vertices) {
        this.vertices = vertices;
    }
    public void addVertex(Vertex v){
        this.vertices.add(v);
    }

    public int[] getCoord() {
        return axialCoord;
    }

    public void setCoord(int[] coord) {
        this.axialCoord = coord;
    }

    @Override
    public String toString() {
        return "Hex{" +
                "resourceType='" + resourceType + '\'' +
                ", value=" + value +
                ", vertices=" + vertices.toString() +
                ", axialCoord=" + Arrays.toString(axialCoord) +
                '}';
    }

    public TextureRegion getTextureRegion() {
        return textureRegion;
    }

    public void setTextureRegion(TextureRegion textureRegion) {
        this.textureRegion = textureRegion;
    }

    public HashSet<Edge> getNeighboringEdges() {
        return edges;
    }

    public void setNeighboringEdges(HashSet<Edge> neighboringEdges) {
        this.edges = neighboringEdges;
    }

    public void addEdge(Edge e){
        this.edges.add(e);
    }

    public float[] getRectCoords() {
        return rectCoords;
    }

    public void setRectCoords(float[] rectCoords) {
        this.rectCoords = rectCoords;
    }
}
