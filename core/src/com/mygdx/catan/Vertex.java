package com.mygdx.catan;

import java.util.Arrays;
import java.util.HashSet;

public class Vertex {
    private HashSet<Hex> neighboringHexes;
    private float[] rectCoords;
    private float[] polygonCoords;
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
        this.polygonCoords = calculateSquareFromPoint(rectCoords,15);

    }

    public void addHexes(Hex h){
        neighboringHexes.add(h);
    }

    public void addEdges(Edge e){
        connectedEdges.add(e);
    }

    public HashSet<Hex> getNeighboringHexes(){
        return this.neighboringHexes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vertex vertex = (Vertex) o;
        return Arrays.equals(rectCoords, vertex.rectCoords);
    }

    public static float[] calculateSquareFromPoint(float[] point, int length){

        // calculate vertices
        float[] square = new float[8];
        square[0] = point[0] + length / 2;
        square[1] = point[1] + length / 2;
        square[2] = point[0] - length / 2;
        square[3] = point[1] + length / 2;
        square[4] = point[0] - length / 2;
        square[5] = point[1] - length / 2;
        square[6] = point[0] + length / 2;
        square[7] = point[1] - length / 2;

        return square;

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

    public float[] getPolygonCoords() {
        return polygonCoords;
    }

    public void setPolygonCoords(float[] polygonCoords) {
        this.polygonCoords = polygonCoords;
    }
}

