package com.mygdx.catan;


import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;

public class Edge {
    HashSet<Hex> neighboringHexes;
    float[][] rectCoords;
    private HashSet<Vertex> connectedVertices;
    private float[] polygonCoords;
    String roadColor;
    com.badlogic.gdx.graphics.g2d.TextureRegion textureRegion ;


    public Edge(float x1, float y1, float x2, float y2){
        this.rectCoords = new float[][]{{x1,y1}, {x2,y2}};
        this.neighboringHexes = new HashSet<Hex>();
        this.connectedVertices = new HashSet<Vertex>();
        this.polygonCoords = calculateRectangleFromLine(rectCoords[0],rectCoords[1],5);
        this.roadColor = "None";
    }

    public static float[] calculateRectangleFromLine(float[] point1, float[] point2, float width) {
        // Calculate vector between points
        float[] v = {point2[0] - point1[0], point2[1] - point1[1]};

        // Calculate a perpendicular to it
        float[] p = {v[1], -v[0]};

        // Normalize the perpendicular vector
        float length = (float)Math.sqrt(p[0] * p[0] + p[1] * p[1]);
        float[] n = {p[0] / length, p[1] / length};

        // Calculate 4 points that form a rectangle
        float[] rectangle = new float[8]; // Array to hold rectangle corners
        rectangle[0] = point1[0] + n[0] * width / 2;
        rectangle[1] = point1[1] + n[1] * width / 2;
        rectangle[2] = point1[0] - n[0] * width / 2;
        rectangle[3] = point1[1] - n[1] * width / 2;
        rectangle[4] = point2[0] + n[0] * width / 2;
        rectangle[5] = point2[1] + n[1] * width / 2;
        rectangle[6] = point2[0] - n[0] * width / 2;
        rectangle[7] = point2[1] - n[1] * width / 2;

        return rectangle;
    }

    public void addHexes(Hex h){
        neighboringHexes.add(h);
    }

    public void addVertex(Vertex v){
        this.connectedVertices.add(v);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Edge edge = (Edge) o;
        return Arrays.equals(rectCoords, edge.rectCoords);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(neighboringHexes, connectedVertices);
        result = 31 * result + Arrays.hashCode(rectCoords);
        return result;
    }

    public float[] getPolygonCoords() {
        return polygonCoords;
    }

    public void setPolygonCoords(float[] polygonCoords) {
        this.polygonCoords = polygonCoords;
    }
}

