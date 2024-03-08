package com.mygdx.catan;

import java.util.HashSet;

public class Vertex {
    HashSet<Hex> neighboringHexes;
    float[] rectCoords;
    HashSet<Edge> connectedEdges;

    public Vertex(float x, float y){
        this.rectCoords = new float[]{x, y};
        neighboringHexes = new HashSet<Hex>();
        connectedEdges = new HashSet<Edge>();
    }

    public void addHexes(Hex h){
        neighboringHexes.add(h);
    }

    public void addEdges(Edge e){
        connectedEdges.add(e);
    }


}

