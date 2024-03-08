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
    public static final String[] resources = new String[]{"WOOD", "SHEEP", "CLAY", "ROCK", "WHEAT"};

    private String resourceType;
    private int value;
    private int[] axialCoord;
    private HashSet<Vertex> vertices;
    private HashSet<Edge> edges;
    private com.badlogic.gdx.graphics.g2d.TextureRegion textureRegion ;

    public Hex(String type, int value, int[] axialCoord) {
        this.resourceType = type;
        this.axialCoord = axialCoord;
        this.value = value;

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

    private TextureRegion getTextureRegion(String resourceType) {
        Pixmap pix = new Pixmap(1,1,Pixmap.Format.RGB888 );
        switch (resourceType){
            case "WOOD":
                pix.setColor(0.76f, 0.6f, 0.42f, 1); // Using a brown color
                break;
            case "WHEAT":
                pix.setColor(0.93f, 0.9f, 0.55f, 1); // A light, golden brown
                break;
            case "ROCK":
                pix.setColor(0.5f, 0.5f, 0.5f, 1); // Medium gray
                break;
            case "CLAY":
                pix.setColor(0.72f, 0.45f, 0.2f, 1); // A reddish-brown
                break;
            case "SHEEP":
                pix.setColor(0.6f, 0.8f, 0.4f, 1); // Light green
                break;
            default:
                pix.setColor(1, 1, 1, 1); // Default to white if none match
                break;
        }
        pix.fill();

        TextureRegion region = new TextureRegion(new Texture(pix));
        pix.dispose(); // Don't forget to dispose of the Pixmap to free memory!
        return region;
    }

    private int getRandValue(){
        Random random = new Random();
        // Generate a random number between 2 and 12
        int randomNumber = 2 + random.nextInt(11);
        return randomNumber;
    }

    private String getRandResource(){
        Random random = new Random();
        int randomResourceIndex = random.nextInt(5);
        return resources[randomResourceIndex];
    }

    public String getType() {
        return resourceType;
    }

    public void setType(String type) {
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
}
