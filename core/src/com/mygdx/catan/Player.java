package com.mygdx.catan;
import com.badlogic.gdx.graphics.Color;

import java.awt.image.AreaAveragingScaleFilter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;


public class Player {
    HashMap<Hex.Resource, Integer> resourceCards;
    ArrayList<String> devCards;
    int turn;
    private Color color;
    int playerId;
    //When a dice is rolled, go over all the vertices, check the hexes and then get dev cards based on that
    private ArrayList<Vertex> verticesOwned;
    private ArrayList<Edge> edgesOwned;

    public Player(int playerId){
        verticesOwned = new ArrayList<>();
        edgesOwned = new ArrayList<>();
        this.playerId = playerId;
        this.turn = 0;
        resourceCards = new HashMap<>();
        resourceCards.put(Hex.Resource.WHEAT, 0);
        resourceCards.put(Hex.Resource.ROCK, 0);
        resourceCards.put(Hex.Resource.WOOD,0);
        resourceCards.put(Hex.Resource.CLAY,0);
        resourceCards.put(Hex.Resource.SHEEP,0);
        switch (playerId){
            case 1:
                this.color = Color.BLUE;
                break;
            case 2:
                this.color = Color.RED;
                break;
            case 3:
                this.color = Color.LIME;
                break;
            case 4:
                this.color = Color.WHITE;
                break;
        }
    }

    Boolean currentlyPlaying;
    private int roads;
    private int longestConnectedRoad;
    private int settlements;
    private int cities;

    private int victoryPoints;

    private int knightCards;

    private boolean hasLongestRoad;
    private boolean hasLargestArmy;

    //state vars
    private boolean isPlacingRoad;
    private boolean isPlacingSettlement;
    private boolean isPlacingCity;


    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void addResource(int amount, Hex.Resource resource){
        this.resourceCards.put(resource, this.resourceCards.get(resource) + amount);
    }

    public void subtractResource(int amount, Hex.Resource resource){
        this.resourceCards.put(resource, this.resourceCards.get(resource) - amount);
    }

    public void colonizeEdge(Edge e){
        this.edgesOwned.add(e);
    }

    public void colonizeVertex(Vertex v){
        this.verticesOwned.add(v);
    }

    public ArrayList<Edge> getEdgesOwned(){
        return this.edgesOwned;
    }

    public ArrayList<Vertex> getVerticesOwned(){
       return this.verticesOwned;
    }

}
