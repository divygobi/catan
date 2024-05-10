package com.mygdx.catan;

import java.util.Arrays;
import java.util.HashSet;

public class Vertex {
    private HashSet<Hex> neighboringHexes;
    private float[] rectCoords;
    private float[] settlementPolygonCoords;
    private float[] cityPolygonCoords;
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
        this.settlementPolygonCoords = calculateSquareFromPoint(rectCoords,15);
        this.cityPolygonCoords = calculatePentagonFromPoint(rectCoords, 15);

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

    public static float[] calculatePentagonFromPoint(float[] center, float sideLength) {
        // Calculate the radius of the circumscribed circle
        float angle = 72f; // Each angle increment for the pentagon vertices
        float radians = (float) Math.toRadians(angle);
        float radius = (float) (sideLength / (2 * Math.sin(Math.PI / 5)));

        // Array to hold the coordinates of the pentagon's vertices
        float[] pentagon = new float[10]; // 5 vertices x 2 coordinates each

        // Calculate each vertex based on center and radius
        for (int i = 0; i < 5; i++) {
            pentagon[2 * i] = center[0] + radius * (float) Math.cos(radians * i);
            pentagon[2 * i + 1] = center[1] + radius * (float) Math.sin(radians * i);
        }

        return pentagon;
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
        if(hasSettlement){
            System.out.println("Upgrading to city");
            return cityPolygonCoords;
        }
        return settlementPolygonCoords;
    }

    public void setSettlementPolygonCoords(float[] polygonCoords) {
        this.settlementPolygonCoords = polygonCoords;
    }

    public void setCityPolygonCoords(float[] polygonCoords) { this.cityPolygonCoords = polygonCoords;}


}

