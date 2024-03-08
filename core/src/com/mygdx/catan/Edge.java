package com.mygdx.catan;


import java.util.ArrayList;
import java.util.HashSet;

public class Edge {
    HashSet<Hex> neighboringHexes;
    float[][] rectCoords;
    HashSet<Vertex> connectedVertices;

    public Edge(float x1, float y1, float x2, float y2){
        this.rectCoords = new float[][]{{x1,y1}, {x2,y2}};
        neighboringHexes = new HashSet<Hex>();
        connectedVertices = new HashSet<Vertex>();

    }

    public void addHexes(Hex h){
        neighboringHexes.add(h);
    }

    public void addVertex(Vertex v){
        this.connectedVertices.add(v);
    }
}
