package com.mygdx.catan;

public class Hex {
    
    private String type;
    private String value;
    private int[][] vertices;
    private int[] coord;

    public Hex(String type, String value, int[][] vertices, int[] coord) {
        this.type = type;
        this.value = value;
        this.vertices = vertices;
        this.coord = coord;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public int[][] getVertices() {
        return vertices;
    }

    public void setVertices(int[][] vertices) {
        this.vertices = vertices;
    }

    public int[] getCoord() {
        return coord;
    }

    public void setCoord(int[] coord) {
        this.coord = coord;
    }
}
    