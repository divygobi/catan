package com.mygdx.catan;

import java.util.Arrays;
import java.util.Random;

public class Hex {
    public static final String[] resources = new String[]{"WOOD", "SHEEP", "CLAY", "ROCK", "WHEAT"};

    private String resourceType;
    private int value;
    private int[][] vertices;
    private int[] axialCoord;

    public Hex(String type, int value, int[] axialCoord) {
        this.resourceType = type;
        this.axialCoord = axialCoord;
        this.value = value;

    }

    public Hex(int[] axialCoord){
        this.axialCoord = axialCoord;
        this.resourceType = getRandResource();
        this.value = getRandValue();
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
}
