package com.mygdx.catan;

import java.util.Arrays;
import java.util.HashSet;

public class Vertex {
    private HashSet<Hex> neighboringHexes;
    private float[] rectCoords;
    private HashSet<Edge> connectedEdges;
    private Player player;
    private boolean hasSettlement;
    private boolean hasCity;
    private boolean hasRobber;

    public Vertex(float x, float y){
        this.rectCoords = new float[]{x, y};
        this.neighboringHexes = new HashSet<Hex>();
        this.connectedEdges = new HashSet<Edge>();
        this.player = null;
        this.hasSettlement = false;
        this.hasCity = false;
        this.hasRobber = false;
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

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public boolean hasSettlement() {
        return hasSettlement;
    }

    public void setHasSettlement(boolean hasSettlement) {
        this.hasSettlement = hasSettlement;
    }

    public boolean hasCity() {
        return hasCity;
    }

    public void setHasCity(boolean hasCity) {
        this.hasCity = hasCity;
    }

    public boolean isHasRobber() {
        return hasRobber;
    }

    public void setHasRobber(boolean hasRobber) {
        this.hasRobber = hasRobber;
    }
}

