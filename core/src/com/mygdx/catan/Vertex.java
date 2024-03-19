package com.mygdx.catan;

import java.util.Arrays;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vertex vertex = (Vertex) o;
        return Arrays.equals(rectCoords, vertex.rectCoords);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(rectCoords);
    }
}

