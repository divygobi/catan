package com.mygdx.catan;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.PolygonSprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.Arrays;
import java.util.Random;

public class Hex {
    public static final String[] resources = new String[]{"WOOD", "SHEEP", "CLAY", "ROCK", "WHEAT"};

    private String resourceType;
    private int value;
    private int[][] vertices;
    private int[] axialCoord;
    private com.badlogic.gdx.graphics.g2d.TextureRegion textureRegion ;

    public Hex(String type, int value, int[] axialCoord) {
        this.resourceType = type;
        this.axialCoord = axialCoord;
        this.value = value;
        Pixmap pix = new Pixmap(1,1,Pixmap.Format.RGB888 );


        switch (resourceType){
            case "WOOD":
                pix.setColor(0.76f, 0.6f, 0.42f, 1); // Using a brown color
            case "WHEAT":
                pix.setColor(0.93f, 0.9f, 0.55f, 1); // A light, golden brown
            case "ROCK":
                pix.setColor(0.5f, 0.5f, 0.5f, 1); // Medium gray
            case "CLAY":
                pix.setColor(0.72f, 0.45f, 0.2f, 1); // A reddish-brown
            case "SHEEP":
                pix.setColor(0.6f, 0.8f, 0.4f, 1); // Light green
        }
        this.textureRegion = getTextureRegion(this.resourceType);
    }

    public Hex(int[] axialCoord){
        this.axialCoord = axialCoord;
        this.resourceType = getRandResource();
        this.value = getRandValue();
        this.textureRegion = getTextureRegion(this.resourceType);
    }

    private TextureRegion getTextureRegion(String resourceType) {
        Pixmap pix = new Pixmap(1,1,Pixmap.Format.RGB888 );
        switch (resourceType){
            case "WOOD":
                pix.setColor(0.76f, 0.6f, 0.42f, 1); // Using a brown color
            case "WHEAT":
                pix.setColor(0.93f, 0.9f, 0.55f, 1); // A light, golden brown
            case "ROCK":
                pix.setColor(0.5f, 0.5f, 0.5f, 1); // Medium gray
            case "CLAY":
                pix.setColor(0.72f, 0.45f, 0.2f, 1); // A reddish-brown
            case "SHEEP":
                pix.setColor(0.6f, 0.8f, 0.4f, 1); // Light green
        }
        return new TextureRegion(new Texture(pix));
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

    public int[][] getVertices() {
        return vertices;
    }

    public void setVertices(int[][] vertices) {
        this.vertices = vertices;
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
                ", vertices=" + Arrays.toString(vertices) +
                ", axialCoord=" + Arrays.toString(axialCoord) +
                '}';
    }

    public TextureRegion getTextureRegion() {
        return textureRegion;
    }

    public void setTextureRegion(TextureRegion textureRegion) {
        this.textureRegion = textureRegion;
    }
}
